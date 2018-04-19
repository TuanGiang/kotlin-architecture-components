package com.giangnt.kidtube.net.playlist

import com.google.gson.annotations.SerializedName

data class PlaylistData(
        @SerializedName("kind")
        val kind: String,

        @SerializedName("id")
        val id: String,

        @SerializedName("snippet")
        val snippet: PlaylistSnippet,

        @SerializedName("contentDetails")
        val contentDetails: PlaylistContentDetails

)