package com.xah.jwtauth

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.StandardCharsets

@SpringBootTest(classes = [JwtAuthApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
class DownloadControllerTest(@LocalServerPort port: String) : StringSpec() {


    init {
        RestAssured.port = port.toInt()
        RestAssured.baseURI = "http://localhost"
        lateinit var token: String

        "auth user" {
            val requestBody = """
                {
                    "userLogin": "username",
                    "userPassword": "password"
                }
            """
            val response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .`when`()
                .post("/user/auth")
                .then()
                .statusCode(200)
        }


    }

}