package com.community.global.exception

import org.springframework.http.HttpStatus

open class BusinessException(
    var errorCode: ErrorCode,
    var errorMessage: String,
    var httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
) : RuntimeException() {
    constructor(errorCode: ErrorCode) : this(
        errorCode,
        errorCode.message,
    )

    constructor(errorCode: ErrorCode, httpStatus: HttpStatus) : this(
        errorCode,
        errorCode.message,
        httpStatus,
    )
}
