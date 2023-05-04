package com.xah.jwtauth.services

import com.xah.jwtauth.dataClasses.UserAuth
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService {

    @Value("\${jwtAuth.secretToken}") private val secretToken: String = ""
    @Value("\${jwtAuth.tokenExpireMilliseconds}") private val tokenExpireMilliseconds: Int = 0
    private val userPassword: String = "UserPasswd"
    private val userLogin: String = "TestUser"


    fun generateJwt(userLogin: String): String = Jwts.builder()
        .setSubject(userLogin)
        .setExpiration(Date(System.currentTimeMillis() + tokenExpireMilliseconds))
        .signWith(Keys.hmacShaKeyFor(secretToken.toByteArray()))
        .compact()

    fun validateUserPassword(user: UserAuth): Boolean =
        ((userLogin == user.userLogin) && (userPassword == user.userPassword))

}