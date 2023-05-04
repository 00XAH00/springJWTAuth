package com.xah.jwtauth.contrllers

import com.xah.jwtauth.dataClasses.UserAuth
import com.xah.jwtauth.services.UserService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class User(private val userService: UserService) {

    @PostMapping("/auth")
    fun userAuth(@RequestBody userAuth: UserAuth, response: HttpServletResponse): String {
        print("user login: ${userAuth.userLogin}\n")
        print("user password: ${userAuth.userPassword}\n")

        if (!userService.validateUserPassword(user = userAuth)) {
            response.status = 401
            return "Invalid user password"
        }

        response.status = 200
        return userService.generateJwt(userAuth.userLogin)
    }
}