package com.test.channel9.data.source.remote.workers

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.test.channel9.domain.models.states.netmodels.NetworkResult
import com.test.channel9.domain.usecases.NewsArticlesUseCase
import com.test.channel9.presentation.uicomponents.notifications.NotificationHandler
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

const val FOREGROUND_SERVICE_CHANNEL = "foreground_service_channel"
const val NOTIFICATION_CHANNEL_NAME = "channel_name"
const val NOTIFICATION_ID = 1001

@HiltWorker
class LatestNewsWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val newsArticlesUseCase: NewsArticlesUseCase,
    private val notificationHandler: NotificationHandler
) : CoroutineWorker(context, params) {

    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {
         return try {
            newsArticlesUseCase.fetchNewsArticles()
                .collect{ result ->
                    when(result){
                        is NetworkResult.Success -> {
                            result.data?.assets?.let {
                                when(newsArticlesUseCase.hasTheLatestNews(it, newsArticlesUseCase.getArticles())){
                                    false -> {
                                        newsArticlesUseCase.setArticles(it)
                                        notificationHandler.createLatestNewsNotification(newsArticlesUseCase.getTheLatestArticle().headline)
                                    }
                                    else -> {}
                                }
                            }

                        }
                        is NetworkResult.Error ->{
                            Log.d(::LatestNewsWorker.name, result._message )
                        }
                    }
                }
            Result.Success()
        } catch (e: Exception){
            Result.failure()
        }

    }

}