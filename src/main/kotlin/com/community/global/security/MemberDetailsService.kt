package com.community.global.security

import com.community.domain.member.domain.MemberRepository
import com.community.global.exception.BusinessException
import com.community.global.exception.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MemberDetailsService(
    private val memberRepository: MemberRepository,
) : UserDetailsService {
    override fun loadUserByUsername(loginId: String?): UserDetails {
        if (loginId.isNullOrEmpty()) {
            throw BusinessException(ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED)
        }
        val member = memberRepository.findByLoginId(loginId) ?: throw BusinessException(ErrorCode.MEMBER_NOT_FOUND, HttpStatus.NOT_FOUND)

        println("member: $member, role : ${member.role}")

        return MemberDetails(member)
    }
}
