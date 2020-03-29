package site.jaymw.JStore.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import site.jaymw.JStore.domain.User;

import java.util.Date;

/**
 * JWT工具类
 */
public class JwtUtils {
    public static final String SUBJECT = "sbwx";
    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;//过期时间。毫秒，一周
    public static final String APPSECRET = "jaymw";//服务端解密的私钥

    /**
     * 生成token
     *
     * @param user
     * @return
     */
    public static String geneToken(User user) {
        if (user == null || user.getId() == null || user.getName() == null || user.getHeadImg() == null) {
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("img", user.getHeadImg())
                .setIssuedAt(new Date())//JWT签发的时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))//过期时间
                .signWith(SignatureAlgorithm.HS256, APPSECRET)//用私钥和ES256算法加密
                .compact();
        return token;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static Claims parseToken(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET)
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (Exception e) {
            //打印日志
            return null;
        }
    }
}