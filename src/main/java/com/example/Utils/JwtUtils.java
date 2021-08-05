package com.example.Utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.User.entity.User;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    private static final String secret = "secret";

    public static String createJWTToken(User user) throws JWTCreationException, UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(JwtUtils.secret);

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        LocalDateTime localDateTime = LocalDateTime.now();
        // 2小时过期
        LocalDateTime expireDate = localDateTime.plusMinutes(120);

        String token = JWT.create()
                .withHeader(map)
                .withClaim("user", JSON.toJSONString(user))
                .withSubject("token")
                .withIssuedAt(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .withExpiresAt(Date.from(expireDate.atZone(ZoneId.systemDefault()).toInstant()))
                .sign(algorithm);
        token = Base64.getEncoder().encodeToString(token.getBytes("utf-8"));
        return token;
    }

    public static User verifyToken(String token){
        token = new String(Base64.getDecoder().decode(token));
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JwtUtils.secret)).build();
        jwtVerifier.verify(token);
        return JSON.parseObject(JWT.decode(token).getClaim("user").asString(), User.class);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        User user = new User();
        String token = JwtUtils.createJWTToken(user);
        System.out.println(token);

    }
}
