package com.giangnt.kidtube.model

import com.google.gson.annotations.SerializedName

data class MovieStatistic(
        @SerializedName("videoId")
        val videoId: String,
        @SerializedName("viewCount")
        val viewCount: Int,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("dislikeCount")
        val dislikeCount: Int
)