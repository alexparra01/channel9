package com.test.channel9.data.source.remote

import android.content.Context
import com.test.channel9.data.source.remote.services.WorkerService
import com.test.channel9.domain.models.NewsArticles
import retrofit2.Response
import javax.inject.Inject

interface NetDataSource {
     suspend fun fetchNewsArticles(): Response<NewsArticles>
     fun setupWorkManager(context: Context)
}

class NetDataSourceImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val workerService: WorkerService
): NetDataSource {
    override suspend fun fetchNewsArticles(): Response<NewsArticles> {
        return newsApi.getNews()
    }

    override fun setupWorkManager(context: Context) {
        workerService.setup(context)
    }
}