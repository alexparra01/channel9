package com.test.channel9.domain.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Asset(
    val url: String,
    val headline: String,
    val theAbstract: String,
    val byLine: String,
    val relatedImages: List<RelatedImage>,
    val timeStamp: Long
) : Parcelable
