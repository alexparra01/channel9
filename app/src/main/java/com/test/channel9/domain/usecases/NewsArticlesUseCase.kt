package com.test.channel9.domain.usecases

import com.test.channel9.domain.models.Asset
import com.test.channel9.domain.models.NewsArticles
import com.test.channel9.domain.models.RelatedImage
import com.test.channel9.domain.models.states.netmodels.NetworkResult
import com.test.channel9.domain.repository.NewsArticlesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import javax.inject.Inject

interface NewsArticlesUseCase {
    suspend fun fetchNewsArticles(): Flow<NetworkResult<NewsArticles>>
    fun sortListDescending(assets: List<Asset>): List<Asset>
    fun getThumbnailImageUrl(images: List<RelatedImage>): RelatedImage
}

class NewsArticlesUseCaseImpl @Inject constructor(
    private val newsArticlesRepository: NewsArticlesRepository
    ): NewsArticlesUseCase {

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
        return images.find { it.type == "thumbnail" } ?: RelatedImage(url = "", type = "")
    }

}
