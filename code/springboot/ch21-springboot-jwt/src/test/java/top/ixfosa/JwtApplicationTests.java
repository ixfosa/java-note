package top.ixfosa;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.ixfosa.dao.UserDao;
import top.ixfosa.dao.impl.UserDaoImpl;
import top.ixfosa.entity.User;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest

class JwtApplicationTests {
    private static final String SIGN_CODE = "hahaha";
    @Test
    public void getTokenTest() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND , 200);

        // withHeader(map): 有默认值 {  "alg": "HS256", "typ": "JWT" }
        String token = JWT.create().withHeader(new HashMap<>())  // header

                .withClaim("id", 1)   // payload
                .withClaim("name", "ixfosa") // payload
                .withExpiresAt(calendar.getTime())   // 指定令牌的过期时间
                .sign(Algorithm.HMAC256(SIGN_CODE));  // 签名, 保密

        System.out.println("token: " + token);
        // token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiaXhmb3NhIiwiaWQiOjEsImV4cCI6MTYyODg0NDU0Mn0.0rnQmMeWIE_2it4dW81itIjqFfRSg99UCshmTIKlXNI
    }

    // 令牌验证:根据令牌和签名解析数据
    // 常见异常：
        //- SignatureVerificationException  签名不一致异常
        //- TokenExpiredException           令牌过期异常
        //- AlgorithmMismatchException      算法不匹配异常
        //- InvalidClaimException           失效的payload异常
    @Test
    public void parseTokenTest() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiaXhmb3NhIiwiaWQiOjEsImV4cCI6MTYyODg0NTEzNH0.lDIv_CVl-TTNIYYJnJySWQQHhIPAb33Hz7b8YkPxKdU";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SIGN_CODE)).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        System.out.println("用户Id: " + verify.getClaim("id").asInt());
        System.out.println("用户名：" + verify.getClaim("name"));
        System.out.println("用户名：" + verify.getClaim("name").asString());
        System.out.println("过期时间：" + verify.getExpiresAt());
        // 用户Id: 1
        // 用户名：com.auth0.jwt.impl.JsonNodeClaim@1bc776b7
        // 用户名：ixfosa
        // 过期时间：Fri Aug 13 16:58:54 CST 2021
    }
}
