package com.giangnt.kidtube.search

import com.giangnt.kidtube.model.Channel

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 10:06 AM - 4/19/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.search - `Channe;SearchClickCallBack`
 */
interface ChannelSearchClickCallBack {
    fun onSelect(channel: Channel)
    fun onRemove(channel: Channel)
}