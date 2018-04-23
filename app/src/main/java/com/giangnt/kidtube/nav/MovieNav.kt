package com.giangnt.kidtube.nav

import com.giangnt.kidtube.model.Channel
import com.giangnt.kidtube.model.MovieItem

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 3:45 PM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.nav - MovieNav
 */
interface MovieNav {
    fun onGoChannelDetail(channel: Channel)
    fun onGoPlayVideo(movieItem: MovieItem, from : String)
}