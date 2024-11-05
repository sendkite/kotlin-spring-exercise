package com.community.domain

class Article(
    val id: Long,
    val title: String,
    val content: String,
    val hashTag: String,
) : BaseEntity()
