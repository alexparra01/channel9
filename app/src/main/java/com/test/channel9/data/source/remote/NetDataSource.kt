package com.test.channel9.data.source.remote

import com.test.channel9.domain.models.NewsArticles
import retrofit2.Response
import javax.inject.Inject

interface NetDataSource {
     suspend fun fetchNewsArticles(): Response<NewsArticles>
}

class NetDataSourceImpl @Inject constructor(private val newsApi: NewsApi): NetDataSource {

    override suspend fun fetchNewsArticles(): Response<NewsArticles> {
        return newsApi.getNews()
    }

}