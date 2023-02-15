package com.apisae.api.services.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author Mauro Simoes
 */

@Service
public class ServiceJWT implements IServiceJWT{

    private static final String KEY = "66546A576E5A7234743777217A25432A462D4A614E645267556B587032733576";

    private static final Long EXPIRATION = TimeUnit.DAYS.toMillis(1); // 1 jour

    @Override
    public String generateToken(UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(new HashMap<>())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String extractUsername(String jwtToken){
        return extractClaim(jwtToken, Claims::getSubject);
    }

    @Override
    public boolean tokenIsValid(String jwtToken, UserDetails userDetails){
        final String username = extractUsername(jwtToken);
        return (username.equals(userDetails.getUsername())) && !tokenIsExpired(jwtToken);
    }


    /**
     * Extrait une information contenu dans un jeton JWT
     *
     * @param jwtToken le jeton
     * @param claimsResolver la fonction correspondant a l'extraction l'information voulue
     * @return l'information
     */
    private <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrait toutes les informations contenues dans un jeton JWT
     *
     * @param jwtToken le jeton
     * @return les informations
     */
    private Claims extractAllClaims(String jwtToken){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Verifie si jeton JWT a expire
     *
     * @param jwtToken le jeton
     * @return vrai si le jeton a expire, false sinon
     */
    private boolean tokenIsExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    /**
     * Extrait la date d'expiration d'un jeton JWT
     *
     * @param jwtToken le jeton
     * @return la date d'expiration
     */
    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken,Claims::getExpiration);
    }

}
