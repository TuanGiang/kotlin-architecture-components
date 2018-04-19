package com.giangnt.kidtube.net.playlist

import com.google.gson.annotations.SerializedName

data class PlaylistContentDetails(
        @SerializedName("itemCount")
        val itemCount: Int
)