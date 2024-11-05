package com.community.global.exception

import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionControllerAdvice {
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(e.errorCode)
        return ResponseEntity.status(e.httpStatus).body(error)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val fieldErrors =
            e.bindingResult.fieldErrors.map {
                FieldError(
                    field = it.field,
                    value = it.rejectedValue.toString(),
                    reason = it.defaultMessage ?: "invalid",
                )
            }

        val errorResponse =
            ErrorResponse(
                errorCode = ErrorCode.BAD_REQUEST,
                fieldErrors = fieldErrors,
            )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpMethodException(e: HttpRequestMethodNotSupportedException): ResponseEntity<ErrorResponse> =
        handleBusinessException(
            BusinessException(
                errorCode = ErrorCode.UNSUPPORTED_HTTP_METHOD,
                httpStatus = HttpStatus.BAD_REQUEST,
            ),
        )

    @ExceptionHandler(
        BindException::class,
        ConstraintViolationException::class,
    )
    fun handleHttpRequestValidationException(e: Exception?): ResponseEntity<ErrorResponse> {
        var errorCode: String? = null

        if (e is BindException) {
            errorCode = (e).bindingResult.allErrors[0].defaultMessage
        }
        if (e is ConstraintViolationException) {
            errorCode =
                e.constraintViolations
                    .iterator()
                    .next()
                    .message
        }

        val error: ErrorCode = ErrorCode.valueOf(errorCode ?: ErrorCode.INTERNAL_SERVER_ERROR.name)
        val status = if (error == ErrorCode.INTERNAL_SERVER_ERROR) HttpStatus.INTERNAL_SERVER_ERROR else HttpStatus.BAD_REQUEST

        return handleBusinessException(
            BusinessException(
                errorCode = error,
                httpStatus = status,
            ),
        )
    }
}
