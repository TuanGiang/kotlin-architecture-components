package com.giangnt.kidtube.net

import com.giangnt.kidtube.model.Channel
import com.google.gson.annotations.SerializedName

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 1:29 PM - 4/19/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.net - SearchData
 */
data class SearchData(
        @SerializedName("kind")
        val kind: String,
        @SerializedName("etag")
        val etag: String,
        @SerializedName("id")
        val id: SearchId,
        @SerializedName("snippet")
        val snippet: SearchSnippet
) {
    fun toChannel(): Channel {
        return Channel(snippet.channelId, "", snippet.thumbnail.medium.url, snippet.title, snippet.description, id.kind, "", "")
    }
}
