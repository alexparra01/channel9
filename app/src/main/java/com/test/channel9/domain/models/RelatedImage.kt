package com.test.channel9.domain.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RelatedImage(
    val url: String,
    val type: String
)