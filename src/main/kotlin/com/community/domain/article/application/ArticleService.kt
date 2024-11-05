package com.community.domain.article.application

import com.community.domain.article.web.ArticleResponse
import org.springframework.stereotype.Service

@Service
class ArticleService {
    fun getArticles(): List<ArticleResponse>? =
        listOf(
            ArticleResponse(
                id = 1,
                title = "title",
                content = "content",
            ),
        )
}
