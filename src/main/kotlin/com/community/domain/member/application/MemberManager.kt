package com.community.domain.member.application

import com.community.domain.member.domain.Member
import com.community.domain.member.domain.MemberRepository
import com.community.domain.member.web.request.SignupRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberManager(
    private val memberRepository: MemberRepository,
) {
    @Transactional
    fun signup(request: SignupRequest) {
        memberRepository.save(
            Member.create(
                loginId = request.loginId,
                email = request.email,
                passwordHash = request.password,
            ),
        )
    }
}
