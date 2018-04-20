package com.giangnt.kidtube.net.playlist.item

import com.google.gson.annotations.SerializedName

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 10:09 AM - 4/20/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.net.playlist.item - PlaylistItemSnippet
 */
data class PlaylistItemSnippet(
        @SerializedName("position")
        val position: Int,

        @SerializedName("playlistId")
        val playlistId: String,

        @SerializedName("resourceId")
        val resourceId: PlaylistItemResourceId
)