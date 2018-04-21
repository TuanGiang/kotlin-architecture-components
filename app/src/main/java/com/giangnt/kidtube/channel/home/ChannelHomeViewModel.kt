package com.giangnt.kidtube.channel.home

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.databinding.ObservableField
import com.giangnt.kidtube.base.viewmodel.LoginViewModel
import com.giangnt.kidtube.entity.AppDatabase
import com.giangnt.kidtube.model.ChannelItem
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.paging.PagingConstants
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
class ChannelHomeViewModel(application: Application, val repo: Repo, val channelId : String) : LoginViewModel(application) {
    private val movieDao = AppDatabase.getInstance(application).movieDao()

    private val allMovieItem = LivePagedListBuilder(movieDao.getMovieChannelHome(channelId), PagedList.Config.Builder()
            .setPageSize(PagingConstants.PAGE_SIZE)
            .setEnablePlaceholders(PagingConstants.ENABLE_PLACEHOLDERS)
            .build()).build()


    fun getObservableMovies(): LiveData<PagedList<MovieItem>> {
        return allMovieItem
    }


    class Factory(val application: Application, val repo: Repo, val channelId: String) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ChannelHomeViewModel(application, repo , channelId) as T
        }
    }
}