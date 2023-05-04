package com.xah.jwtauth.services

import com.xah.jwtauth.dataClasses.UserAuth
import com.xah.jwtauth.security.JWTUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val jwtUtil: JWTUtil) {

    @Value("\${spring.security.user.name}") private lateinit var userLogin: String
    @Value("\${spring.security.user.password}") private lateinit var userPassword: String


    fun generateJwt(userLogin: String): String = jwtUtil.generateToken(userLogin)

    fun getUserLogin(): String {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
        return userDetails.username
    }

    fun validateUserPassword(user: UserAuth): Boolean =
        ((userLogin == user.userLogin) && (userPassword == user.userPassword))

}