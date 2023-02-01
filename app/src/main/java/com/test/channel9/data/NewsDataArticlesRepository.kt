package com.test.channel9.data

import android.content.Context
import com.test.channel9.data.factory.NewsDataFactory
import com.test.channel9.domain.models.NewsArticles
import com.test.channel9.domain.repository.NewsArticlesRepository
import retrofit2.Response
import javax.inject.Inject

class NewsDataArticlesRepository @Inject constructor(private val newsDataFactory: NewsDataFactory): NewsArticlesRepository {
    override suspend fun fetchNewsArticles(): Response<NewsArticles> {
        return newsDataFactory.fetchNewsArticles()
    }

    override fun setupWorkManager(context: Context) {
        newsDataFactory.setupWorkManager(context)
    }
}