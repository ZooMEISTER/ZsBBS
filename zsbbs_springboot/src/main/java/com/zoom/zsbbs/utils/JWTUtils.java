package com.zoom.zsbbs.utils;

import com.fasterxml.jackson.core.JsonParseException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

public class JWTUtils {
    //过期时间
    private static long expire = 604800;

    //密钥
    private static String secret = "qwertyuiopasdfghjklzxcvbnmqweqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklrtyuiopasdfghjkl";

    //生成token
    public static String generateToken(int userid){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * expire);

        System.out.println("expiration" + expiration);

        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setSubject(String.valueOf(userid))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    //解析token
    public static Claims getClaimsByToken(String token) {

        Claims claims;
        claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return claims;

    }
}
