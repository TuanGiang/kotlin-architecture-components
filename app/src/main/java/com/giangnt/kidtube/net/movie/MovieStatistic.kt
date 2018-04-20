package com.giangnt.kidtube.net.movie

import com.google.gson.annotations.SerializedName

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 11:26 AM - 4/20/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.net.movie - MovieStatistic
 */

data class MovieStatistic(
        @SerializedName("viewCount")
        val viewCount: Int,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("dislikeCount")
        val dislikeCount: Int,
        @SerializedName("favoriteCount")
        val favoriteCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int
)