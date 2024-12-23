package com.community.global.security.jwt

import com.community.global.security.MemberDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.Date
import javax.crypto.SecretKey
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Component
class JwtUtil(
    private val memberDetailsService: MemberDetailsService,
    private val userSecretKeys: Map<String, String>,
) {
    // HS256 / 8 = 32
    val defaultSecretKey = "abcdefghijklmnopqrstuvwxyz1234567890"

    private fun getSecretKeyFromToken(token: String): SecretKey {
        val claims = unsafeGetClaims(token)
        return getSecretKey(claims["loginId"] as String)
    }

    @OptIn(ExperimentalEncodingApi::class)
    private fun unsafeGetClaims(token: String): Map<String, Any> {
        val splitToken = token.split(".")
        val decodedBytes = Base64.decode(splitToken[0])
        val header = String(decodedBytes).replace("HS256", "none")
        val encodedHeader = Base64.encode(header.encodeToByteArray())
        val unSignedToken = encodedHeader + "." + splitToken[1] + "."
        val claims =
            Jwts
                .parser()
                .unsecured()
                .build()
                .parse(unSignedToken)
                .payload
        return claims as Map<String, Any>
    }

    fun generateToken(
        loginId: String,
        claims: HashMap<String, String>,
    ): String {
        val key = getSecretKey(loginId)
        val now = Instant.now()

        return Jwts
            .builder()
            .claims(claims)
            .subject(loginId)
            .issuedAt(Date(now.toEpochMilli()))
            .expiration(Date(now.plusSeconds(3600).toEpochMilli()))
            .signWith(key, Jwts.SIG.HS256)
            .compact()
    }

    fun getClaims(token: String): Jws<Claims> {
        val key = getSecretKeyFromToken(token)
        try {
            return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
        } catch (e: ExpiredJwtException) {
            throw ExpiredJwtException(null, null, "토큰이 만료되었습니다.")
        }
    }

    private fun getSecretKey(loginId: String): SecretKey {
        val secretKey = userSecretKeys[loginId] ?: defaultSecretKey
        return Keys.hmacShaKeyFor(secretKey.toByteArray())
    }

    fun getUsername(token: String): String = getClaims(token).payload.subject

    fun isTokenExpired(token: String): Boolean {
        val claims = getClaims(token)
        return claims.payload.expiration.before(Date())
    }

    fun validateToken(
        token: String,
        username: String,
    ): Boolean =
        try {
            !isTokenExpired(token) && getClaims(token).payload.subject == username
        } catch (e: Exception) {
            false
        }

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)
        val userDetails = memberDetailsService.loadUserByUsername(claims.payload.subject)
        return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
    }
}
