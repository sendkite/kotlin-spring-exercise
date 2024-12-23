package com.community.domain.member.domain

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<BaseMember, Long> {
    fun existsByEmail(email: String): Boolean

    fun findByLoginId(loginId: String): BaseMember?
}
