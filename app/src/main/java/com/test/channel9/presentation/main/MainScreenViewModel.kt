package com.test.channel9.presentation.main

import android.content.Context
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.channel9.domain.models.Asset
import com.test.channel9.domain.models.RelatedImage
import com.test.channel9.domain.models.states.netmodels.NetworkResult
import com.test.channel9.domain.usecases.NewsArticlesUseCase
import com.test.channel9.presentation.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val newsArticlesUseCase: NewsArticlesUseCase
    ): ViewModel() {

    private var _refreshState = MutableStateFlow(false)
    val refreshState = _refreshState.asStateFlow()
    private var _state = MutableStateFlow<MainScreenState>(MainScreenState.ShowLoading)
    val state = _state.asStateFlow()

    fun fetchNewsArticles () {
        viewModelScope.launch {
            newsArticlesUseCase.fetchNewsArticles()
                .onStart {
                    _state.value = MainScreenState.ShowLoading
                }
                .collect {
                    when(it){
                        is NetworkResult.Success -> {
                            it.data?.assets?.let { articles ->
                                newsArticlesUseCase.setArticles(articles)
                                _state.value = MainScreenState.ShowData
                            }
                            Log.d(::MainActivity.name, Constants.SUCCESS)
                        }
                        is NetworkResult.Error -> {
                            _state.value = MainScreenState.ShowError(it.message ?: "")
                            Log.d(::MainActivity.name, Constants.Error)
                        }
                    }

            }
        }
    }

    fun articlesList(): List<Asset> {
        return newsArticlesUseCase.sortListDescending(newsArticlesUseCase.getArticles())
    }

    fun findThumbnailImage(relatedImages: List<RelatedImage>) = newsArticlesUseCase.getThumbnailImageUrl(relatedImages)

    sealed class MainScreenState {
        object ShowLoading: MainScreenState()
        object ShowData: MainScreenState()
        data class ShowError(val errorMessage: String): MainScreenState()
    }

    fun setupWorkManager(context: Context) {
        newsArticlesUseCase.setupWorkManager(context = context)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun setArticlesList(assets : List<Asset>){
        newsArticlesUseCase.setArticles(assets)
    }
}