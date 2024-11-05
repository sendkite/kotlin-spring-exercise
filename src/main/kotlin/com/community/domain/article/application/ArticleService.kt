package com.community.domain.article.application

import com.community.domain.article.ArticleRepository
import com.community.domain.article.web.ArticleResponse
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val articleRepository: ArticleRepository,
) {
    fun getArticles(): List<ArticleResponse> =
        articleRepository.findAll().map {
            ArticleResponse(
                id = it.id,
                title = it.title,
                content = it.content,
            )
        }
}
