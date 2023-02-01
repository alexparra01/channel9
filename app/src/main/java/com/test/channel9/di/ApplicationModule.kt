package com.test.channel9.di

import android.content.Context
import androidx.work.*
import com.test.channel9.data.NewsDataArticlesRepository
import com.test.channel9.data.factory.NewsDataFactory
import com.test.channel9.data.source.remote.NetDataSource
import com.test.channel9.data.source.remote.NetDataSourceImpl
import com.test.channel9.data.source.remote.NewsApi
import com.test.channel9.data.source.remote.services.WorkerService
import com.test.channel9.data.source.remote.services.WorkerServiceImpl
import com.test.channel9.domain.repository.NewsArticlesRepository
import com.test.channel9.presentation.uicomponents.notifications.NotificationHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideNetDataSource(newsApi: NewsApi, workerService: WorkerService): NetDataSource {
        return NetDataSourceImpl(newsApi, workerService)
    }
    @Singleton
    @Provides
    fun provideNewsDataFactory(netDataSource: NetDataSource): NewsDataFactory {
        return NewsDataFactory(netDataSource)
    }
    @Singleton
    @Provides
    fun provideNewsArticlesRepository(newsDataFactory: NewsDataFactory): NewsArticlesRepository {
        return NewsDataArticlesRepository(newsDataFactory)
    }
    @Singleton
    @Provides
    fun provideWorker(): WorkerService {
        return WorkerServiceImpl()
    }

    @Singleton
    @Provides
    fun provideNotificationHandler(@ApplicationContext context: Context): NotificationHandler {
        return NotificationHandler(context)
    }
}