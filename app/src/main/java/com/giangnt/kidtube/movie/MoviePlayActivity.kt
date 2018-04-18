package com.giangnt.kidtube.movie

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.giangnt.kidtube.R
import com.giangnt.kidtube.databinding.ActivityMoviePlayBinding
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.repo.Repo

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 10:19 AM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.movie - MoviePlayActivity
 */
class MoviePlayActivity : AppCompatActivity() {

    lateinit var binding: ActivityMoviePlayBinding
    lateinit var model: MoviePlayViewModel
    lateinit var adapter: PlayMovieAdapter

    lateinit var movie: MovieItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_play)

        movie = intent.extras.getSerializable(ITEM) as MovieItem

        adapter = PlayMovieAdapter()
        binding.rcMovie.adapter = adapter

        val factory = MoviePlayViewModel.Factory(
                application, Repo(), movie.video.id)
        model = ViewModelProviders.of(this, factory)
                .get(MoviePlayViewModel::class.java)
        model.setMovie(movie)
        binding.movieModel = model
        subscribeUi(model)
    }

    private fun subscribeUi(model: MoviePlayViewModel) {
        model.getObservableMovies().observe(this, Observer<ArrayList<MovieItem>> { items ->
            adapter.setData(movie, items)
        })

    }

    companion object {
        public val ITEM = "ITEM"
        fun getIntent(context: Context, item: MovieItem): Intent {
            val intent = Intent(context, MoviePlayActivity::class.java)
            intent.putExtra(ITEM, item)
            return intent
        }
    }

}