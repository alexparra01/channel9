package com.test.channel9.di

import com.test.channel9.data.NewsDataArticlesRepository
import com.test.channel9.data.factory.NewsDataFactory
import com.test.channel9.data.source.remote.NetDataSource
import com.test.channel9.data.source.remote.NetDataSourceImpl
import com.test.channel9.data.source.remote.NewsApi
import com.test.channel9.domain.repository.NewsArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideNetDataSource(newsApi: NewsApi): NetDataSource {
        return NetDataSourceImpl(newsApi)
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
}