package com.community.domain.article.web

import com.community.domain.article.application.ArticleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/articles")
class ArticleController(
    private val articleService: ArticleService,
) {
    @GetMapping
    fun getArticles(): ResponseEntity<List<ArticleResponse>> = ResponseEntity.ok(articleService.getArticles())
}
