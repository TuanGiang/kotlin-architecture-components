package com.giangnt.kidtube.net.playlist

import com.google.gson.annotations.SerializedName

data class PlaylistSnippet(

        @SerializedName("publishedAt")
        val publishedAt: String,

        @SerializedName("channelId")
        val channelId: String,

        @SerializedName("title")
        val title: String,

        @SerializedName("description")
        val description: String,

        @SerializedName("channelTitle")
        val channelTitle: String,

        @SerializedName("thumbnails")
        val thumbnail: PlaylistThumbnail

)