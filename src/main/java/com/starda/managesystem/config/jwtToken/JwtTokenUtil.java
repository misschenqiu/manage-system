package com.starda.managesystem.config.jwtToken;

import com.starda.managesystem.pojo.SysUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config.jwtToken
 * @ClassName: JwtTokenUtil
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/20 23:51
 * @Version: 1.0
 */

@Component
@Log4j2
public class JwtTokenUtil {

    /**
     * 私钥（盐值）
     */
    private  static  final  String SECRET_KEY="starda.manage";

    /**
     * 过去时间  毫秒，设置默认一周的时间过期  3600000L* 24 * 7
     */
    private  static  final  long EXPIRATION_TIME=3600000L* 24;

    /**
     * 生成令牌
     */
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>(2);
        //把用户名和签发时间封装在claims中
        claims.put(Claims.SUBJECT, userDetails.getUsername());
        claims.put(Claims.ISSUED_AT, new Date());
        return generateToken(claims);
    }
    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            log.info("claims = " + claims.toString());
            username = claims.getSubject();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
        return username;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) throws  Exception{
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            log.info("令牌已经过期");
            new Throwable(e);
        }
        return true;
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(Claims.ISSUED_AT, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证令牌
     *
     * @param token       令牌
     * @param userDetails 用户
     * @return 是否有效
     */
    public Boolean validateToken(String token, UserDetails userDetails) throws Exception {
        User user = (User) userDetails;
        String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        //这个时间是一周后的现在   就是过期时间
        Date expirationDate = new Date(System.currentTimeMillis()+ EXPIRATION_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) throws Exception {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error("token中获取数据失败" + e.getMessage());
            new Throwable(e);
        }
        return claims;
    }

}
