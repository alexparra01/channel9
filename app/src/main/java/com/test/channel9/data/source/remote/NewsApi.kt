package com.test.channel9.data.source.remote

import com.test.channel9.data.util.Constants
import com.test.channel9.domain.models.NewsArticles
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsApi {
    @Headers("Accept: application/json")
    @GET(Constants.NEWS_RELATIVE_URL)
    suspend fun getNews(): Response<NewsArticles>
}