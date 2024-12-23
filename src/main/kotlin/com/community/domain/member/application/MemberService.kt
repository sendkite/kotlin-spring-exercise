package com.community.domain.member.application

import com.community.domain.member.domain.Member
import com.community.domain.member.domain.MemberRepository
import com.community.domain.member.web.request.SignupRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    @Transactional
    fun signup(request: SignupRequest) {
        memberRepository.save(
            Member.create(
                email = request.email,
                passwordHash = passwordEncoder.encode(request.password),
                loginId = request.loginId,
            ),
        )
    }
}
