package com.mauricio.sahao.lanchonete.security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class TokenAutenticacaoService {

    private static final long UM_DIA_EXPIRATION_TIME = 864000000;
    private static final String CHAVE_ASSINATURA = "LanchoneteFINCH";
    private static final String TOKEN_PREFIX = "Token";
    private static final String HEADER_STRING = "Authorization";
    private static final String EMPTY = "";
    private static final String BLANK = " ";

    public static void addAuthentication(HttpServletResponse res, String username) {
        String JWT = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + UM_DIA_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, CHAVE_ASSINATURA)
                .compact();

        String token = TOKEN_PREFIX + BLANK + JWT;
        res.addHeader(HEADER_STRING, token);

        try {
            res.getOutputStream().print(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Authentication getByToken(String token) {
        String user = Jwts.parser().setSigningKey(CHAVE_ASSINATURA)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, EMPTY)).getBody().getSubject();

        return user != null ? new UsernamePasswordAuthenticationToken(user, null, null) : null;
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            return getByToken(token);
        }
        return null;
    }
}