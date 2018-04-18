package com.giangnt.kidtube.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
        @SerializedName("id")
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
        val updatedAt: String,
        @SerializedName("playlistId")
        val playlistId: String,
        @SerializedName("channelId")
        val channelId: String,
        @SerializedName("channelTitle")
        val channelTitle: String,
        @SerializedName("videoId")
        val videoId: String,
        @SerializedName("duration")
        val duration: String,
        @SerializedName("dimension")
        val dimension: String,
        @SerializedName("definition")
        val definition: String,
        @SerializedName("viewCount")
        val viewCount: Int,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("dislikeCount")
        val dislikeCount: Int

) : Serializable