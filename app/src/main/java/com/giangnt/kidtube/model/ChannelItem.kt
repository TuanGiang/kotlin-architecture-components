package com.giangnt.kidtube.model

import java.io.Serializable

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 1:20 PM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.model - ChannelItem
 */
data class ChannelItem(
        val channel: Channel,
        val channelStatistic: ChannelStatistic
) : Serializable