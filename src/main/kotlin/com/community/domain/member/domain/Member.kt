package com.community.domain.member.domain

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("NORMAL")
class Member(
    loginId: String,
    email: String,
    passwordHash: String,
) : BaseMember(role = "ROLE_MEMBER", loginId = loginId, email = email, passwordHash = passwordHash) {
    companion object {
        fun create(
            loginId: String,
            email: String,
            passwordHash: String,
        ): Member = Member(loginId, email, passwordHash)
    }
}
