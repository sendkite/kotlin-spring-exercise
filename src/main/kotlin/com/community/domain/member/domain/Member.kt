package com.community.domain.member.domain

import com.community.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.Instant

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false, unique = true)
    var loginId: String = "",
    @Column(nullable = false)
    var passwordHash: String = "",
    @Column(nullable = false)
    var email: String = "",
    @Column(nullable = false)
    var lastLoginAt: Instant = Instant.now(),
) : BaseEntity() {
    companion object {
        fun create(
            loginId: String,
            passwordHash: String,
            email: String,
        ): Member = Member(loginId = loginId, passwordHash = passwordHash, email = email)
    }

    fun updateLastLoginAt() {
        lastLoginAt = Instant.now()
    }
}
