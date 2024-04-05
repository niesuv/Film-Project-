package niesuv.project.film.service;

import io.jsonwebtoken.*;
import niesuv.project.film.EmbeddedType.ROLE;
import niesuv.project.film.customException.UnvalidJWT;
import niesuv.project.film.entity.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    private final String JWT_SECRET = "lodaaaaaa";

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 604800000L;

    // Tạo ra jwt từ thông tin user
    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .claim("ROLE", userDetails.getRole())
                .claim("username", userDetails.getUsername())
                .compact();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        }
        catch (JwtException exception) {
            throw new UnvalidJWT(exception.getMessage());
        }

    }

    public UserDetails getUserDetailsFromToken(String token) {
        UserDetails userDetails = new UserDetails();
        if (validateToken(token)) {
            Claims claims = Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            userDetails.setId(Long.valueOf(claims.getSubject()));
            userDetails.setUsername(claims.get("username", String.class));
            userDetails.setRole(ROLE.valueOf(claims.get("ROLE", String.class)));
            return userDetails;
        }
        return null; // just for warning disappear, the func just return userDetails or throw exception
    }

}
