package com.community.global.security

import com.community.global.security.jwt.JwtRequestFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.UUID

@Configuration
class SecurityConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtRequestFilter: JwtRequestFilter,
    ): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .httpBasic {}
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/v1/auths/signup", "/v1/auths/login")
                    .permitAll()
                    .requestMatchers("/authenticate")
                    .permitAll()
                    .requestMatchers("/changeSecretKey")
                    .permitAll()
                    .anyRequest()
                    .denyAll()
            }
        return http.build()
    }

    @Bean("userSecretKeys")
    fun userSecretKeys(): Map<String, String> {
        val secretKeys = mutableMapOf<String, String>()
        for (i in 1..2) {
            val key = UUID.randomUUID().toString()
            println("key : $key")
            // admin
            secretKeys["member$i@email.com"] = key
        }
        return secretKeys
    }
}
