package com.giangnt.kidtube.channel.movies

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.giangnt.kidtube.base.viewmodel.LoginViewModel
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.repo.Repo

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 4:02 PM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.channel.movies - ChannelVideoViewModel
 */
class ChannelVideoViewModel (application: Application, var repo: Repo) : LoginViewModel(application) {

    val movieLiveData = MutableLiveData<ArrayList<MovieItem>>()

    var pageIndex = 0

    init {
        movieLiveData.postValue(repo.getHome(pageIndex))
    }

    fun getObservableMovies(): LiveData<ArrayList<MovieItem>> {
        return movieLiveData
    }

    fun getMore() {
        pageIndex++
        val items = repo.getHome(pageIndex)
        val currentItems = movieLiveData.value
        currentItems!!.addAll(items)
        movieLiveData.postValue(currentItems)
    }


    class Factory(val application: Application, val repo: Repo) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ChannelVideoViewModel(application, repo) as T
        }
    }
}