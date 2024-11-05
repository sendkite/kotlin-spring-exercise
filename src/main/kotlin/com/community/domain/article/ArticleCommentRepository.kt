package com.community.domain.article

import org.springframework.data.jpa.repository.JpaRepository

interface ArticleCommentRepository : JpaRepository<ArticleComment, Long>
