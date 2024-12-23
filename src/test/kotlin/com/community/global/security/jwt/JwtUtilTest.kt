package com.community.global.security.jwt

import io.kotest.core.spec.style.FunSpec
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JwtUtilTest : FunSpec() {
    @Autowired
    lateinit var jwtUtil: JwtUtil

    @Test
    fun generateToken() {
        val token = jwtUtil.generateToken("test", hashMapOf("email" to "member1@email.com", "role" to "MEMBER", "loginId" to "test"))
        println(token)

        val payload = jwtUtil.getClaims(token).payload
        assert(payload["role"] == "MEMBER")
        assert(payload["email"] == "member1@email.com")
        assert(payload["loginId"] == "test")
        assert(payload.subject == "test")
    }
}
