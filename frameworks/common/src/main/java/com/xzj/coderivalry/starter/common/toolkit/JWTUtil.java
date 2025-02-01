package com.xzj.coderivalry.starter.common.toolkit;

import com.alibaba.fastjson2.JSON;
import com.xzj.coderivalry.starter.common.constant.UserConstant;
import com.xzj.coderivalry.starter.common.entiry.UserInfoDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 */
@Slf4j
public class JWTUtil {

    private static final long EXPIRATION = 86400L;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ISS = "coderivalry";
    public static final String SECRET = "a3f1c1d4e5b6c7f8a9b0c1e2d3f4a5b6c7d8e9f0a1b2c3d4e5f6g7h8i9j0k1";

    /**
     * 生成用户Token
     *
     * @param userInfoDTO 用户信息
     * @return 用户Token
     */
    public static String generateJWTToken(UserInfoDTO userInfoDTO) {
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put(UserConstant.USER_NAME_KEY, userInfoDTO.getUsername());
        String jwtToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuedAt(new Date())
                .setIssuer(ISS)
                .setSubject(JSON.toJSONString(userInfoMap))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000 * 30))
                .compact();
        return TOKEN_PREFIX + jwtToken;
    }

    /**
     * 解析用户 Token
     *
     * @param jwtToken 用户访问 Token
     * @return 用户信息
     */
    public static UserInfoDTO parseJwtToken(String jwtToken) {
        if (StringUtils.hasText(jwtToken)) {
            String actualJwtToken = jwtToken.replace(TOKEN_PREFIX, "");
            try {
                Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(actualJwtToken).getBody();
                Date expiration = claims.getExpiration();
                if (expiration.after(new Date())) {
                    String subject = claims.getSubject();
                    return JSON.parseObject(subject, UserInfoDTO.class);
                }
            } catch (ExpiredJwtException ignored) {
            } catch (Exception ex) {
                log.error("JWT Token解析失败，请检查", ex);
            }
        }
        return null;
    }
}
