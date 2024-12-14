package com.community.domain.member.application

import com.community.domain.member.annotation.EmailUnique
import com.community.domain.member.domain.MemberRepository
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.stereotype.Component
import java.text.MessageFormat

@Component
class EmailUniqueValidator(
    private val memberRepository: MemberRepository,
) : ConstraintValidator<EmailUnique, String> {
    override fun initialize(constraintAnnotation: EmailUnique) {}

    override fun isValid(
        email: String,
        context: ConstraintValidatorContext,
    ): Boolean {
        val isExistEmail: Boolean = memberRepository.existsByEmail(email)

        if (isExistEmail) {
            context.disableDefaultConstraintViolation()
            context
                .buildConstraintViolationWithTemplate(
                    MessageFormat.format("Email {0} already exists!", email),
                ).addConstraintViolation()
        }
        return !isExistEmail
    }
}
