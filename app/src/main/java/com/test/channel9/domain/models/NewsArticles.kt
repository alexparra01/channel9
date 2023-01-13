package com.test.channel9.domain.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class NewsArticles(
    val assets: List<Asset>
) : Parcelable
