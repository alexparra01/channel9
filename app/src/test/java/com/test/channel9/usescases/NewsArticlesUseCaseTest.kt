package com.test.channel9.usescases

import com.google.common.truth.Truth
import com.test.channel9.domain.models.states.netmodels.NetworkResult
import com.test.channel9.domain.usecases.NewsArticlesUseCase
import com.test.channel9.presentation.main.MainScreenViewModel
import com.test.channel9.util.CoroutineTestRule
import com.test.channel9.util.Samples
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * This [Unit] test covers all the transformations in NewsArticlesUseCase.
 *
 * * Fetch articles and get a successful response and ShowData state
 *
 * * Fetch articles and get a error response and ShowError state
 *
 * * Organise assets list by timestamp so that the latest is at the top
 *
 * * Get the smallest image thumbnail from the list of related images
 * **/

const val THUMBNAIL = "thumbnail"

@RunWith(MockitoJUnitRunner::class)
class NewsArticlesUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var newsArticlesUseCase: NewsArticlesUseCase

    private lateinit var mainScreenViewModel: MainScreenViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mainScreenViewModel = MainScreenViewModel(newsArticlesUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch articles and get a successful response and ShowData state`() = runTest {
        //Given
        Mockito.`when`(newsArticlesUseCase.fetchNewsArticles()).thenReturn(flow { emit(NetworkResult.Success(Samples.newsArticles)) })

        //When
        mainScreenViewModel.fetchNewsArticles()

        //Then
        Mockito.verify(newsArticlesUseCase, Mockito.times(1)).fetchNewsArticles()
        Truth.assertThat(mainScreenViewModel.state.value).isEqualTo(MainScreenViewModel.MainScreenState.ShowData)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch articles and get a error response and ShowError state`() = runTest {
        //Given
        Mockito.`when`(newsArticlesUseCase.fetchNewsArticles()).thenReturn(flow { emit(NetworkResult.Error("Invalid number of parameters")) })

        //When
        mainScreenViewModel.fetchNewsArticles()

        //Then
        Mockito.verify(newsArticlesUseCase, Mockito.times(1)).fetchNewsArticles()
        Truth.assertThat(mainScreenViewModel.state.value).isEqualTo(MainScreenViewModel.MainScreenState.ShowError("Invalid number of parameters"))
    }

    @Test
    fun `organise assets list by timestamp so that the latest is at the top`() {
        //Given
        Mockito.`when`(newsArticlesUseCase.sortListDescending(Samples.newsArticles.assets)).thenReturn(Samples.newsArticles.assets.sortedByDescending { it.timeStamp })
        mainScreenViewModel.setArticlesList(Samples.newsArticles.assets)

        //When
        val sortedList = mainScreenViewModel.articlesList()

        //Then
        Mockito.verify(newsArticlesUseCase, Mockito.times(1)).sortListDescending(Samples.newsArticles.assets)
        Truth.assertThat(sortedList[0].timeStamp).isEqualTo(Samples.newsArticles.assets[1].timeStamp)
    }

    @Test
    fun `get the smallest image thumbnail from the list of related images`() {
        //Given
        Mockito.`when`(newsArticlesUseCase.getThumbnailImageUrl(Samples.newsArticles.assets[0].relatedImages)).thenReturn(Samples.newsArticles.assets[0].relatedImages.find { it.type == THUMBNAIL })

        //When
        val smallImage = mainScreenViewModel.findThumbnailImage(Samples.newsArticles.assets[0].relatedImages)

        //Then
        Mockito.verify(newsArticlesUseCase, Mockito.times(1)).getThumbnailImageUrl(Samples.newsArticles.assets[0].relatedImages)
        Truth.assertThat(smallImage.type).isEqualTo(THUMBNAIL)

    }
}