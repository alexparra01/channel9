package com.test.channel9.domain.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsArticles(
    val assets: List<Asset>
)
