package com.giangnt.kidtube.net.movie

import com.giangnt.kidtube.net.playlist.item.PlaylistItemResourceId
import com.google.gson.annotations.SerializedName

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 11:20 AM - 4/20/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.net - MovieSnippet
 */
data class MovieSnippet(
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
        val thumbnail: MovieThumbnail,

        @SerializedName("playlistId")
        val playlistId: String,

        @SerializedName("resourceId")
        val resourceId: PlaylistItemResourceId
)