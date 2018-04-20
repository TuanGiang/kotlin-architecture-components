package com.giangnt.kidtube.net.playlist

import com.giangnt.kidtube.model.Playlist
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

) {
    fun toPlayList(): Playlist {
        return Playlist(id, snippet.publishedAt, snippet.thumbnail.medium.url,
                snippet.title, kind, "", "", "", contentDetails.itemCount,
                snippet.channelId, snippet.channelTitle)
    }
}