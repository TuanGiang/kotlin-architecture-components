package com.giangnt.kidtube.channel.detail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.giangnt.kidtube.channel.home.ChannelHomeFragment
import com.giangnt.kidtube.channel.movies.ChannelVideoFragment
import com.giangnt.kidtube.channel.playlist.ChannelPlaylistFragment
import com.giangnt.kidtube.model.Channel

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 3:07 PM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.channel.detail - ChannelPagerAdapter
 */
class ChannelPagerAdapter(supportFragmentManager: FragmentManager, val channel: Channel) : FragmentStatePagerAdapter(supportFragmentManager) {
    private val COUNT = 3
    private val home: ChannelHomeFragment
    private val video: ChannelVideoFragment
    private val playlist: ChannelPlaylistFragment

    init {
        home = ChannelHomeFragment.newInstance(channel)
        video = ChannelVideoFragment.newInstance(channel.channelId)
        playlist = ChannelPlaylistFragment.newInstance(channel.channelId)
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> home
            1 -> video
            else -> playlist
        }

    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Home"
            1 -> "Video"
            else -> "Playlist"
        }
    }
}