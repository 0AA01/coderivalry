package com.xzj.coderivalry.biz.userservice.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzj.coderivalry.biz.userservice.common.constant.RedisConstant;
import com.xzj.coderivalry.biz.userservice.dao.entity.UserDO;
import com.xzj.coderivalry.biz.userservice.dao.mapper.UserMapper;
import com.xzj.coderivalry.biz.userservice.dto.req.UserLoginReqDTO;
import com.xzj.coderivalry.biz.userservice.dto.req.UserRegisterReqDTO;
import com.xzj.coderivalry.biz.userservice.service.UserService;
import com.xzj.coderivalry.biz.userservice.vo.UserLoginVO;
import com.xzj.coderivalry.framework.starter.convention.errorcode.BaseErrorCode;
import com.xzj.coderivalry.framework.starter.convention.exception.ClientException;
import com.xzj.coderivalry.framework.starter.cache.DistributedCache;
import com.xzj.coderivalry.starter.common.entiry.UserInfoDTO;
import com.xzj.coderivalry.starter.common.toolkit.JWTUtil;
import jodd.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 用户接口实现层
 *
 * @author xzj
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final UserMapper userMapper;
    private final RedissonClient redissonClient;
    private final DistributedCache distributedCache;
    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if (StringUtil.isBlank(requestParam.getUsername()) || StringUtil.isBlank(requestParam.getPassword())) {
            throw new ClientException(BaseErrorCode.SERVICE_ERROR);
        }
        if (requestParam.getUsername().length() < 5 || requestParam.getUsername().length() > 20) {
            throw new ClientException(BaseErrorCode.USER_NAME_VERIFY_ERROR);
        }
        if (requestParam.getPassword().length() < 6 || requestParam.getPassword().length() > 30) {
            throw new ClientException(BaseErrorCode.PASSWORD_VERIFY_ERROR);
        }
        if (userRegisterCachePenetrationBloomFilter.contains(requestParam.getUsername())) {
            throw new ClientException(BaseErrorCode.USER_NAME_EXIST_ERROR);
        }
        RLock lock = redissonClient.getLock(String.format(RedisConstant.LOCK_USER_REGISTER_KEY, requestParam.getUsername()));
        if (!lock.tryLock()) {
            throw new ClientException(BaseErrorCode.USER_NAME_EXIST_ERROR);
        }
        try {
            UserDO userDO = new UserDO();
            userDO.setUsername(requestParam.getUsername());
            userDO.setPassword(requestParam.getPassword());
            userMapper.insert(userDO);
            userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
        } catch (DuplicateKeyException ex) {
            userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
            throw new ClientException(BaseErrorCode.USER_NAME_EXIST_ERROR);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public UserLoginVO login(UserLoginReqDTO requestParam) {
        //todo 可以考虑缓存空值，防止缓存穿透，使用lua减少网络io
        if (!userRegisterCachePenetrationBloomFilter.contains(requestParam.getUsername())) {
            throw new ClientException(BaseErrorCode.USER_NOT_EXIST);
        }
        RLock lock = redissonClient.getLock(String.format(RedisConstant.LOCK_USER_LOGIN_KEY, requestParam.getUsername()));
        if (!lock.tryLock()) {
            throw new ClientException(BaseErrorCode.SERVICE_ERROR);
        }
        try {
            LambdaQueryWrapper<UserDO> lambdaQueryWrapper = Wrappers.lambdaQuery(UserDO.class)
                    .eq(UserDO::getUsername, requestParam.getUsername())
                    .eq(UserDO::getPassword, requestParam.getPassword());
            UserDO userDO = userMapper.selectOne(lambdaQueryWrapper);
            if (userDO == null) {
                throw new ClientException(BaseErrorCode.USER_NOT_EXIST);
            }
            UserInfoDTO userInfo = new UserInfoDTO();
            userInfo.setUsername(userDO.getUsername());
            String token = JWTUtil.generateJWTToken(userInfo);
            distributedCache.put(token, JSON.toJSONString(userDO), 30, TimeUnit.DAYS);
            UserLoginVO result = new UserLoginVO();
            result.setUsername(userDO.getUsername());
            result.setToken(token);
            return result;
        } finally {
            lock.unlock();
        }
    }
}
