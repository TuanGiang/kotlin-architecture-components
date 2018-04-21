package com.giangnt.kidtube.channel.home

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giangnt.kidtube.R
import com.giangnt.kidtube.base.fragment.LoadDataFragment
import com.giangnt.kidtube.databinding.FragmentChannelHomeBinding
import com.giangnt.kidtube.home.HomeAdapter
import com.giangnt.kidtube.home.HomeClickCallback
import com.giangnt.kidtube.model.Channel
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.nav.MovieNav
import com.giangnt.kidtube.repo.Repo
import com.giangnt.kidtube.support.EndlessRecyclerViewScrollListener

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 2:17 PM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.channel - ChannelHomeFragment
 */
class ChannelHomeFragment : LoadDataFragment(), HomeClickCallback {

    lateinit var channel: Channel
    lateinit var model: ChannelHomeViewModel
    lateinit var binding: FragmentChannelHomeBinding
    lateinit var homeAdapter: HomeAdapter

    var nav: MovieNav? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MovieNav) {
            nav = context
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if (activity is MovieNav) {
            nav = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        channel = arguments!!.get(CHANNEL) as Channel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_channel_home, container, false)

        homeAdapter = HomeAdapter(this)
        binding.rcMovie.adapter = homeAdapter
        val mDividerItemDecoration = DividerItemDecoration(binding.rcMovie.context,
                DividerItemDecoration.VERTICAL)
        binding.rcMovie.addItemDecoration(mDividerItemDecoration)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = ChannelHomeViewModel.Factory(
                activity!!.application, Repo(), channel.channelId)

        model = ViewModelProviders.of(this, factory)
                .get(ChannelHomeViewModel::class.java)

        binding.channel = channel
        subscribeUi(model)
    }

    private fun subscribeUi(model: ChannelHomeViewModel) {
        model.getObservableMovies().observe(this,Observer(homeAdapter::submitList))
    }

    override fun onClick(movieItem: MovieItem) {
        nav?.onGoPlayVideo(movieItem)
    }

    override fun onClickChannel(channel: Channel) {
        nav?.onGoChannelDetail(channel)
    }


    companion object {
        val CHANNEL = "CHANNEL"
        fun newInstance(channel: Channel): ChannelHomeFragment {
            val args = Bundle()
            args.putSerializable(CHANNEL, channel)
            val fragment = ChannelHomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}