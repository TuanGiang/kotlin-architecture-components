package com.giangnt.kidtube.model

import com.google.gson.annotations.SerializedName

data class Channel(
        val id: String,
        @SerializedName("publicAt")
        val publicAt: String,
        @SerializedName("thumbnail")
        val thumbnail: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("source")
        val source: String,
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("updatedAt")
        val updatedAt: String
)