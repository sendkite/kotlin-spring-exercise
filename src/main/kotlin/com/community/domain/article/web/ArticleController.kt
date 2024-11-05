package com.community.domain.article.web

import com.community.domain.article.application.ArticleService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/articles")
class ArticleController(
    private val articleService: ArticleService,
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping
    fun getArticles(): ResponseEntity<List<ArticleResponse>> {

        logger.info { "info log test" }

        return ResponseEntity.ok(articleService.getArticles())
    }
}
