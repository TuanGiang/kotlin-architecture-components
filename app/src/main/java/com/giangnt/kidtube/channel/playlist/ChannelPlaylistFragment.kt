package com.giangnt.kidtube.channel.playlist

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giangnt.kidtube.R
import com.giangnt.kidtube.base.fragment.DataFragment
import com.giangnt.kidtube.databinding.FragmentChannelPlaylistBinding
import com.giangnt.kidtube.model.Playlist
import com.giangnt.kidtube.nav.PlaylistNav
import com.giangnt.kidtube.repo.Repo

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 2:16 PM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.channel - ChannelPlaylistFragment
 */
class ChannelPlaylistFragment : DataFragment(), ChannelPlaylistCallback {

    lateinit var binding: FragmentChannelPlaylistBinding
    lateinit var channelPlaylistAdapter: ChannelPlaylistAdapter
    lateinit var model: ChannelPlaylistViewModel
    lateinit var channelId: String

    var nav: PlaylistNav? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is PlaylistNav) {
            nav = context
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if (activity is PlaylistNav) {
            nav = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        channelId = arguments!!.getString(CHANNEL_ID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_channel_playlist, container, false)

        channelPlaylistAdapter = ChannelPlaylistAdapter(this, this)
        binding.rcPlaylist.adapter = channelPlaylistAdapter
        val mDividerItemDecoration = DividerItemDecoration(binding.rcPlaylist.context,
                DividerItemDecoration.VERTICAL)
        binding.rcPlaylist.addItemDecoration(mDividerItemDecoration)

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = ChannelPlaylistViewModel.Factory(
                activity!!.application, Repo(), channelId!!)

        model = ViewModelProviders.of(this, factory)
                .get(ChannelPlaylistViewModel::class.java)

        subscribeUi(model)
    }

    override fun onClick(playlist: Playlist) {
        nav?.onGoPlaylist(playlist)
    }

    private fun subscribeUi(viewModel: ChannelPlaylistViewModel) {

        viewModel.getObservablePlaylist().observe(this, Observer(channelPlaylistAdapter::submitList))
    }

    companion object {
        val CHANNEL_ID = "CHANNEL_ID"
        public fun newInstance(channelId: String): ChannelPlaylistFragment {
            val args = Bundle()
            args.putString(CHANNEL_ID, channelId)
            val fragment = ChannelPlaylistFragment()
            fragment.arguments = args
            return fragment
        }
    }
}