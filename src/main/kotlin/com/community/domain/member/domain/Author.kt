package com.community.domain.member.domain

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("AUTHOR")
class Author(
    loginId: String,
    email: String,
    passwordHash: String,
) : BaseMember(role = "ROLE_AUTHOR", loginId = loginId, email = email, passwordHash = passwordHash)
