package com.giangnt.kidtube.net.playlist.item

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 12:59 PM - 4/20/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.net.playlist.item - PlaylistItem
 */
data class PlaylistItem(
        val videoId: String,
        val position: Int,
        val playlistId: String
)