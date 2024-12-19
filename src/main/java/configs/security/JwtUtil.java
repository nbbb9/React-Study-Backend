package configs.security;
// https://velog.io/@hhss2259/JWT%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%B4%EC%84%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B3%BC%EC%A0%95-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0
// https://sjh9708.tistory.com/170
// https://velog.io/@musimco/JWT-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B3%A0-%EC%93%B0%EC%9E%90
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    @Value("${jwt.secret-key}")
    private static String SECRET_KEY;

    private static final long EXPIRE_ACCESS_TOKEN = 1000 * 60 * 60; // 만료시간 1시간

    private static final String TOKEN_INVALID_MESSAGE = "Token is invalid";
    private static final String TOKEN_EXPIRED_MESSAGE = "Token is expired";

    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_ACCESS_TOKEN))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims validateToken(String token) {//검증 토큰
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
