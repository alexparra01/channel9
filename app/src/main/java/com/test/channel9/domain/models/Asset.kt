package com.test.channel9.domain.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Asset(
    val url: String,
    val headline: String,
    val theAbstract: String,
    val byLine: String,
    val relatedImages: List<RelatedImage>,
    val timeStamp: Long
)
