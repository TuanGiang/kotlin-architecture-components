package com.giangnt.kidtube.net.movie

import com.giangnt.kidtube.net.Thumbnail
import com.google.gson.annotations.SerializedName

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 11:22 AM - 4/20/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.net.movie - MovieThumbnail
 */
data class MovieThumbnail(
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