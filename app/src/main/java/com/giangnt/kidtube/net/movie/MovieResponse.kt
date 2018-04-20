package com.giangnt.kidtube.net.movie

import com.google.gson.annotations.SerializedName

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 11:30 AM - 4/20/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.net.movie - MovieResponse
 */

data class MovieResponse(
        @SerializedName("items")
        val items: ArrayList<MovieData>
)