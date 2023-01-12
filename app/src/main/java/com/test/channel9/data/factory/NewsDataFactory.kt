package com.test.channel9.data.factory

import com.test.channel9.data.source.remote.NetDataSource
import javax.inject.Inject

class NewsDataFactory @Inject constructor(
    private val netDataSource: NetDataSource
) {
    suspend fun fetchNewsArticles() = netDataSource.fetchNewsArticles()
}