package com.giangnt.kidtube.movie

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.giangnt.kidtube.BuildConfig
import com.giangnt.kidtube.R
import com.giangnt.kidtube.databinding.ActivityMoviePlayBinding
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.repo.Repo
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment


/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 10:19 AM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.movie - MoviePlayActivity
 */
class MoviePlayActivity : AppCompatActivity(), PlayMovieClickCallback {
    lateinit var binding: ActivityMoviePlayBinding
    lateinit var model: MoviePlayViewModel
    lateinit var adapter: PlayMovieAdapter

    lateinit var movie: MovieItem
    var openFrom: String = FROM_HOME
    var mPlayer: YouTubePlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_play)

        movie = intent.extras.getSerializable(ITEM) as MovieItem
        openFrom = intent.extras.getString(FROM)

        adapter = PlayMovieAdapter(this)
        binding.rcMovie.adapter = adapter

        val factory = MoviePlayViewModel.Factory(
                application, Repo(), movie, openFrom)
        model = ViewModelProviders.of(this, factory)
                .get(MoviePlayViewModel::class.java)


        binding.movieModel = model
        subscribeUi(model)


    }

    override fun onClick(movieItem: MovieItem) {
        model.setMovie(movieItem)
    }

    fun initYouTubePlayer(videoId: String) {
        val youtubeFragment = fragmentManager.findFragmentById(R.id.player) as YouTubePlayerFragment
        youtubeFragment.initialize(BuildConfig.API_KEY,
                object : YouTubePlayer.OnInitializedListener {
                    override fun onInitializationSuccess(provider: YouTubePlayer.Provider,
                                                         youTubePlayer: YouTubePlayer, b: Boolean) {
                        mPlayer = youTubePlayer
                        youTubePlayer.loadVideo(videoId)

                    }

                    override fun onInitializationFailure(provider: YouTubePlayer.Provider,
                                                         youTubeInitializationResult: YouTubeInitializationResult) {

                    }
                })
    }

    private fun subscribeUi(model: MoviePlayViewModel) {
        model.getObservableMovies().observe(this, Observer<List<MovieItem>> { items ->
            adapter.setData(movie, items)
        })

        model.getObservableMovie().observe(this, Observer<MovieItem> { item ->
            if (item != null) {
                if(mPlayer == null) {
                    initYouTubePlayer(item.video.videoId)
                }else {
                    mPlayer!!.loadVideo(item.video.videoId)
                }
            }

        })

    }

    companion object {
        val ITEM = "ITEM"
        val FROM = "FROM"
        val FROM_HOME = "HOME"
        val FROM_CHANNEL = "CHANNEL"
        val FROM_PLAYLIST = "PLAYLIST"
        val FROM_MY_VIDEO = "MY_VIDEO"

        fun getIntent(context: Context, item: MovieItem, from: String): Intent {
            val intent = Intent(context, MoviePlayActivity::class.java)
            intent.putExtra(ITEM, item)
            intent.putExtra(FROM, from)
            return intent
        }
    }

}