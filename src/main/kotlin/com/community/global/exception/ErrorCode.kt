package com.community.global.exception

enum class ErrorCode(
    val message: String,
) {
    BAD_REQUEST("Bad Request"),
    UNAUTHORIZED("Unauthorized"),
    FORBIDDEN("Forbidden"),
    NOT_FOUND("Not Found"),
    METHOD_NOT_ALLOWED("Method Not Allowed"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    MEMBER_NOT_FOUND("Member Not Found"),
    UNSUPPORTED_HTTP_METHOD("Unsupported HTTP Method"),
    MEMBER_LOGIN_ID_DUPLICATED("Member Login ID Duplicated"),
    MEMBER_EMAIL_DUPLICATED("Member Email Duplicated"),
}
