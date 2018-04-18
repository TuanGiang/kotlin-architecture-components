package com.giangnt.kidtube.channel.playlist

import com.giangnt.kidtube.model.Playlist

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 4:36 PM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.channel.playlist - ChannelPlaylistCallback
 */
interface ChannelPlaylistCallback {
    fun onClick(playlist: Playlist)
}