package com.giangnt.kidtube.model

import com.google.gson.annotations.SerializedName

data class MovieStatistic(
        @SerializedName("videoId")
        val videoId: String
)