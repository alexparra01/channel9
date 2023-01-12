package com.test.channel9.di

import com.test.channel9.domain.repository.NewsArticlesRepository
import com.test.channel9.domain.usecases.NewsArticlesUseCase
import com.test.channel9.domain.usecases.NewsArticlesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCases {

    @Singleton
    @Provides
    fun provideNewsArticlesUseCase(newsArticlesRepository: NewsArticlesRepository): NewsArticlesUseCase {
        return NewsArticlesUseCaseImpl(newsArticlesRepository)
    }
}