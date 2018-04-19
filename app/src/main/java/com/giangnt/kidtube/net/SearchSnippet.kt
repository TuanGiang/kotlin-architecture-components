package com.giangnt.kidtube.net

import com.google.gson.annotations.SerializedName

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 1:23 PM - 4/19/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.net - SearchSnippet
 */
data class SearchSnippet(
        @SerializedName("channelId")
        val channelId: String,

        @SerializedName("title")
        val title: String,

        @SerializedName("description")
        val description: String,

        @SerializedName("thumbnails")
        val thumbnail: SearchThumbnail
)
