package com.giangnt.kidtube.channel.home

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableField
import com.giangnt.kidtube.base.viewmodel.LoginViewModel
import com.giangnt.kidtube.model.ChannelItem
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.repo.Repo

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 3:12 PM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.channel.home - ChannelHomeViewModel
 */
class ChannelHomeViewModel(application: Application, val repo: Repo) : LoginViewModel(application) {
    val channel = ObservableField<ChannelItem>()
    val movies = MutableLiveData<ArrayList<MovieItem>>()
    var pageIndex = 0

    init {
        val firstData = repo.getHome(0)
        movies.postValue(firstData)
    }


    public fun getObservableMovies(): MutableLiveData<ArrayList<MovieItem>> {
        return movies
    }

    public fun setChannel(channelItem: ChannelItem?) {
        this.channel.set(channelItem)
    }

    fun getMore() {
        pageIndex++
        val items = repo.getHome(pageIndex)
        val currentItems = movies.value
        currentItems!!.addAll(items)
        movies.postValue(currentItems)
    }

    class Factory(val application: Application, val repo: Repo) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ChannelHomeViewModel(application, repo) as T
        }
    }
}