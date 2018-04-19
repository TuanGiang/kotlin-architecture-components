package com.giangnt.kidtube.net.playlist

import com.google.gson.annotations.SerializedName

data class PlaylistResponse (
        @SerializedName("nextPageToken")
        val nextPageToken : String,

        @SerializedName("items")
        val items : ArrayList<PlaylistData>

)