package com.community.domain.member.web

import com.community.domain.member.application.MemberManager
import com.community.domain.member.web.request.SignupRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auths")
class MemberAuthController(
    private val memberManager: MemberManager,
) {
    @PostMapping("/signup")
    fun signup(
        @Valid @RequestBody request: SignupRequest,
    ): ResponseEntity<Unit> {
        memberManager.signup(request)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
