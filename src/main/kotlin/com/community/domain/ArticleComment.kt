package com.community.domain

class ArticleComment(
    val id: Long,
    val articleId: Long,
    val content: String,
) : BaseEntity()
