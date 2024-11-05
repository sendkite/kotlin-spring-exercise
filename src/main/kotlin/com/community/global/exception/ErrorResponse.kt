package com.community.global.exception

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ErrorResponse(
    val errorCode: String,
    val errorMessage: String,
    val errorDetail: List<FieldError> = emptyList(),
) {
    constructor(errorCode: ErrorCode) : this(
        errorCode.name,
        errorCode.message,
    )

    constructor(errorCode: ErrorCode, fieldErrors: List<FieldError>) : this(
        errorCode.name,
        errorCode.message,
        fieldErrors,
    )
}

data class FieldError(
    val field: String,
    val value: String,
    val reason: String,
)
