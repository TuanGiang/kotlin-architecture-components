package com.giangnt.kidtube.channel.movies

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
import com.giangnt.kidtube.databinding.FragmentChannelVideoBinding
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.movie.MoviePlayActivity
import com.giangnt.kidtube.nav.MovieNav
import com.giangnt.kidtube.repo.Repo

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 2:15 PM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.channel - ChannelVideoFragment
 */
class ChannelVideoFragment : DataFragment(), ChannelMovieCallback {

    lateinit var binding: FragmentChannelVideoBinding
    lateinit var channelMovieAdapter: ChannelMovieAdapter
    lateinit var model: ChannelVideoViewModel

    var nav: MovieNav? = null

    lateinit var channelId: String

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
        channelId = arguments!!.getString(CHANNEL_ID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_channel_video, container, false)

        channelMovieAdapter = ChannelMovieAdapter(this, this)
        binding.rcMovie.adapter = channelMovieAdapter
        val mDividerItemDecoration = DividerItemDecoration(binding.rcMovie.context,
                DividerItemDecoration.VERTICAL)
        binding.rcMovie.addItemDecoration(mDividerItemDecoration)

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = ChannelVideoViewModel.Factory(
                activity!!.application, Repo(), channelId)

        model = ViewModelProviders.of(this, factory)
                .get(ChannelVideoViewModel::class.java)

        subscribeUi(model)
    }

    private fun subscribeUi(viewModel: ChannelVideoViewModel) {

        viewModel.getObservableMovies().observe(this, Observer(channelMovieAdapter::submitList))
    }

    override fun onClick(movieItem: MovieItem) {
        nav?.onGoPlayVideo(movieItem, MoviePlayActivity.FROM_CHANNEL)
    }

    companion object {
        val CHANNEL_ID = "CHANNEL_ID"
        public fun newInstance(channelId: String): ChannelVideoFragment {
            val args = Bundle()
            args.putString(CHANNEL_ID, channelId)
            val fragment = ChannelVideoFragment()
            fragment.arguments = args
            return fragment
        }
    }
}