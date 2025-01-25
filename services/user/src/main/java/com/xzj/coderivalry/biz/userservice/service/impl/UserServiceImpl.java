package com.xzj.coderivalry.biz.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import com.xzj.coderivalry.biz.userservice.common.constant.RedisConstant;
import com.xzj.coderivalry.biz.userservice.config.RBloomFilterConfiguration;
import com.xzj.coderivalry.biz.userservice.dao.entiry.UserDO;
import com.xzj.coderivalry.biz.userservice.dao.mapper.UserMapper;
import com.xzj.coderivalry.biz.userservice.dto.req.UserRegisterReqDTO;
import com.xzj.coderivalry.biz.userservice.service.UserService;
import com.xzj.coderivalry.framework.starter.convention.errorcode.BaseErrorCode;
import com.xzj.coderivalry.framework.starter.convention.exception.ClientException;
import jodd.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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
}
