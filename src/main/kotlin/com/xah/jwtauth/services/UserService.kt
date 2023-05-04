package com.xah.jwtauth.services

import com.xah.jwtauth.dataClasses.UserAuth
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService {

    private val userPassword: String = "UserPasswd"
    private val userLogin: String = "TestUser"

    fun generateJwt(): String = Jwts.builder()
        .setSubject("user@example.com")
        .setExpiration(Date(System.currentTimeMillis() + 3600000)) // 1 hour
        .signWith(Keys.hmacShaKeyFor("Mega Super SecretKey Powered By XAH in 2023".toByteArray()))
        .compact()

    fun validateUserPassword(user: UserAuth): Boolean =
        ((userLogin == user.userLogin) && (userPassword == user.userPassword))

}