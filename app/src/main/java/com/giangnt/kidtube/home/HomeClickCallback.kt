package com.giangnt.kidtube.home

import com.giangnt.kidtube.model.Channel
import com.giangnt.kidtube.model.MovieItem

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 11:31 AM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.home - HomeClickCallback
 */
interface HomeClickCallback {
    fun onClick(movieItem: MovieItem)
    fun onClickChannel(channel: Channel)
}