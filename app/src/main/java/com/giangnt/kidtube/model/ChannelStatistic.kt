package com.giangnt.kidtube.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 1:21 PM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.model - ChannelStatistic
 */
data class ChannelStatistic(
        @SerializedName("channelId")
        val channelId: String,

        @SerializedName("videoCount")
        val videoCount: Int,

        @SerializedName("playlistCount")
        val playlistCount: Int

) : Serializable