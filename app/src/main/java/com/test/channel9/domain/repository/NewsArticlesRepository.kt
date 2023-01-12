package com.test.channel9.domain.repository

import com.test.channel9.domain.models.NewsArticles
import retrofit2.Response

interface NewsArticlesRepository {
    suspend fun fetchNewsArticles(): Response<NewsArticles>
}