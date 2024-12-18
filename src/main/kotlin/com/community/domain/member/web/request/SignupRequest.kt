package com.community.domain.member.web.request

import com.community.domain.member.validation.EmailUnique
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class SignupRequest(
    @field:NotBlank(message = "loginId is required")
    val loginId: String,
    @field:NotBlank(message = "email is required")
    @EmailUnique
    @Email
    val email: String,
    @field:NotBlank(message = "password is required")
    val password: String,
)
