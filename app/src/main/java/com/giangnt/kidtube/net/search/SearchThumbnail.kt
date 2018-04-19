package com.giangnt.kidtube.net.search

import com.giangnt.kidtube.net.Thumbnail
import com.google.gson.annotations.SerializedName

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 1:25 PM - 4/19/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.net - SearchThumbnail
 */
class SearchThumbnail(
        @SerializedName("default")
        val default: Thumbnail,

        @SerializedName("medium")
        val medium: Thumbnail,

        @SerializedName("high")
        val high: Thumbnail
)