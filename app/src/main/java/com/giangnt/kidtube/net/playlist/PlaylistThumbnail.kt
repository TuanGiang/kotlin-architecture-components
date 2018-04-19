package com.giangnt.kidtube.net.playlist

import com.giangnt.kidtube.net.Thumbnail
import com.google.gson.annotations.SerializedName

data class PlaylistThumbnail(
        @SerializedName("default")
        val default: Thumbnail,

        @SerializedName("medium")
        val medium: Thumbnail,

        @SerializedName("high")
        val high: Thumbnail,

        @SerializedName("standard")
        val standard: Thumbnail,

        @SerializedName("maxres")
        val maxres: Thumbnail
)