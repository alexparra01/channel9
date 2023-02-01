package com.test.channel9.domain.usecases

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.test.channel9.domain.models.Asset
import com.test.channel9.domain.models.NewsArticles
import com.test.channel9.domain.models.RelatedImage
import com.test.channel9.domain.models.states.netmodels.NetworkResult
import com.test.channel9.domain.repository.NewsArticlesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

const val SMALL_IMAGE_TYPE = "thumbnail"
interface NewsArticlesUseCase {
    suspend fun fetchNewsArticles(): Flow<NetworkResult<NewsArticles>>
    fun sortListDescending(assets: List<Asset>): List<Asset>
    fun getThumbnailImageUrl(images: List<RelatedImage>): RelatedImage
    fun hasTheLatestNews(assets: List<Asset>, oldAssets: List<Asset>):Boolean
    fun setArticles(assets: List<Asset>)
    fun getArticles():List<Asset>
    fun getTheLatestArticle(): Asset
    fun setupWorkManager(context: Context)

}

class NewsArticlesUseCaseImpl @Inject constructor(
    private val newsArticlesRepository: NewsArticlesRepository
    ): NewsArticlesUseCase {

   var articlesList: List<Asset> by mutableStateOf(emptyList())
        private set
    override suspend fun fetchNewsArticles(): Flow<NetworkResult<NewsArticles>> {
        return flow {
            try {
                val response = newsArticlesRepository.fetchNewsArticles()
                if (response.isSuccessful){
                    emit(NetworkResult.Success(response.body()))
                } else {
                    emit(NetworkResult.Error(response.message()))
                }
            } catch (e: Exception){
                emit(NetworkResult.Error(e.message ?: ""))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun sortListDescending(assets: List<Asset>): List<Asset> {
        return assets.sortedByDescending { it.timeStamp }
    }

    override fun getThumbnailImageUrl(images: List<RelatedImage>): RelatedImage {
        return images.find { it.type == SMALL_IMAGE_TYPE } ?: RelatedImage(url = "", type = "")
    }

    override fun hasTheLatestNews(assets: List<Asset>, oldAssets: List<Asset>): Boolean {
        return if(oldAssets.isNotEmpty() && assets.isNotEmpty()){
            sortListDescending(oldAssets).first() == sortListDescending(assets).first()
        } else {
            oldAssets.isEmpty() && assets.isNotEmpty()
        }
    }

    override fun setArticles(assets: List<Asset>) {
        articlesList = assets
    }

    override fun getArticles(): List<Asset> {
        return articlesList
    }

    override fun getTheLatestArticle(): Asset {
        return sortListDescending(articlesList).first()
    }

    override fun setupWorkManager(context: Context) {
        newsArticlesRepository.setupWorkManager(context)
    }

}
