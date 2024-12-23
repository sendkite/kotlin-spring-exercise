package com.community.global.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtRequestFilter(
    private val jwtUtil: JwtUtil,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val header = request.getHeader("Authorization")
        var username: String? = null
        var accessToken: String? = null
        val tokenPrefix = "Bearer "
        if (!header.isNullOrEmpty() && header.startsWith(tokenPrefix)) {
            accessToken = header.substring(tokenPrefix.length)
            username = jwtUtil.getUsername(accessToken)
        }

        if (!username.isNullOrEmpty() &&
            jwtUtil.validateToken(accessToken!!, username) &&
            SecurityContextHolder.getContext().authentication == null
        ) {
            SecurityContextHolder.getContext().authentication = jwtUtil.getAuthentication(accessToken)
        }
        filterChain.doFilter(request, response)
    }
}
