package com.giangnt.kidtube.channel

import com.giangnt.kidtube.model.ChannelItem

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 1:33 PM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.channel - ChannelClickCallback
 */
interface ChannelClickCallback {
    fun onClick(channelItem: ChannelItem)
}