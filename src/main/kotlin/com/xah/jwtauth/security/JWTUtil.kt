package com.xah.jwtauth.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey


@Component
class JWTUtil {

    val secretToken: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)
    @Value("\${jwtAuth.tokenExpireMilliseconds}")
    private var tokenExpireMilliseconds: Int = 0

    fun generateToken(userLogin: String): String {
        val claims = Jwts.claims().setSubject(userLogin)


        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(Date(System.currentTimeMillis() + tokenExpireMilliseconds))
            .signWith(secretToken)
            .compact()
    }

    fun getUsernameFromToken(token: String): String {
        return Jwts.parserBuilder().setSigningKey(secretToken).build().parseClaimsJws(token).body.subject
    }

    fun validateToken(token: String): Boolean {
        val claims = Jwts.parserBuilder().setSigningKey(secretToken).build().parseClaimsJws(token).body
        val expiration = claims.expiration
        return expiration.after(Date())
    }

    fun extractToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization")
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7)
        }
        return null
    }


}
