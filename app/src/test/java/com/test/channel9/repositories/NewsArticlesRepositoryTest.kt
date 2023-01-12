package com.test.channel9.repositories

import com.google.common.truth.Truth
import com.test.channel9.domain.repository.NewsArticlesRepository
import com.test.channel9.domain.usecases.NewsArticlesUseCase
import com.test.channel9.domain.usecases.NewsArticlesUseCaseImpl
import com.test.channel9.util.CoroutineTestRule
import com.test.channel9.util.Samples
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

/**
 * This [Unit] test covers all the backend calls so this test is important to know what king data is getting before the transformation
 *
 * * Fetch articles and get a successful response and ShowData state
 *
 * * Fetch articles and get a error response
 *
 * ### Consideration
 * Retrofit mock "Response.error()" by default
 * **/

const val ERROR_MESSAGE = "Response.error()"

@RunWith(MockitoJUnitRunner::class)
class NewsArticlesRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var repository: NewsArticlesRepository

    private lateinit var newsArticlesUseCase: NewsArticlesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        newsArticlesUseCase = NewsArticlesUseCaseImpl(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch articles and get a successful response`() = runTest {

        //Given
        Mockito.`when`(repository.fetchNewsArticles()).thenReturn(Response.success(Samples.newsArticles))

        //When
        newsArticlesUseCase.fetchNewsArticles().collect {

            //Then
            Mockito.verify(repository, Mockito.times(1)).fetchNewsArticles()
            Truth.assertThat(it.data).isEqualTo(Samples.newsArticles)

        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch articles and get a error response`() = runTest {
        //Given
        Mockito.`when`(repository.fetchNewsArticles()).thenReturn(Response.error(401,"Response.error()".toResponseBody()))

        //When
        newsArticlesUseCase.fetchNewsArticles().collect {
            //Then
            Mockito.verify(repository, Mockito.times(1)).fetchNewsArticles()
            Truth.assertThat(it.message).isEqualTo(ERROR_MESSAGE)
        }
    }

}