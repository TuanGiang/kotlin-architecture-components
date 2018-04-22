package com.giangnt.kidtube.movie

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableField
import com.giangnt.kidtube.base.viewmodel.LoginViewModel
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.repo.Repo

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 9:13 AM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.movie - MoviePlayViewModel
 */
class MoviePlayViewModel(application: Application, val repo: Repo, val videoId: String) : LoginViewModel(application) {

    val movieItems = MutableLiveData<ArrayList<MovieItem>>()
    val movieItem = ObservableField<MovieItem>()

    init {
        val items = repo.getRelated(videoId)
        setList(items)
    }

    public fun getObservableMovies(): MutableLiveData<ArrayList<MovieItem>> {
        return movieItems
    }

    public fun setMovie(movie: MovieItem?) {
        this.movieItem.set(movie)
    }

    fun setList(items: ArrayList<MovieItem>) {
        movieItems.postValue(items)
    }



    class Factory(val application: Application, val repo: Repo, val videoId: String) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MoviePlayViewModel(application, repo, videoId) as T
        }
    }


}