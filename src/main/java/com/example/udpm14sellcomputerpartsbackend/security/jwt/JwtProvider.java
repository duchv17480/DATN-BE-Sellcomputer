package com.example.udpm14sellcomputerpartsbackend.security.jwt;

import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import com.example.udpm14sellcomputerpartsbackend.security.UserDetailServiceImpl;
import com.example.udpm14sellcomputerpartsbackend.ultil.Constants;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    //class nay co tac dung tao chuoi token
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    private String jwtSecret = "sell-computer";

    private int jwtExpiration = 86400;

    public String createToken(Authentication authentication) {
        CustomerDetailService customerDetailService = (CustomerDetailService) authentication.getPrincipal();
        return Jwts.builder().setSubject(customerDetailService.getUsername())
                .claim(Constants.AUTHEN_INFO.AUTHORITIES_KEY, customerDetailService.getAuthorities())
                .claim(Constants.AUTHEN_INFO.FULL_NAME,customerDetailService.getFullname())
                .setIssuedAt(new Date()).setExpiration(new Date(new Date()
                        .getTime() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (SignatureException e) {
            logger.error("valid JWT signature - Message:{}", e);
        } catch (MalformedJwtException e) {
            logger.error("the token invalid format :{}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("unsupported jwt token - Message:{}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token - Message:{}", e);
        } catch (IllegalArgumentException e) {
            logger.error("The JWT token empty - Message:{}", e);
        }
        return false;
    }

    public String getUserNameFromToken(String token) {
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }
}
