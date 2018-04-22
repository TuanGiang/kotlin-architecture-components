package com.giangnt.kidtube.owner

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.giangnt.kidtube.base.viewmodel.LoginViewModel
import com.giangnt.kidtube.entity.AppDatabase
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.paging.PagingConstants
import com.giangnt.kidtube.repo.Repo

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 10:28 AM - 4/17/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.owner - MyVideoViewModel
 */
class MyVideoViewModel(application: Application, var repo: Repo) : LoginViewModel(application) {

    private val movieDao = AppDatabase.getInstance(application).movieDao()

    private val allMyMovieItem = LivePagedListBuilder(movieDao.getAllMyVideo(), PagedList.Config.Builder()
            .setPageSize(PagingConstants.PAGE_SIZE)
            .setEnablePlaceholders(PagingConstants.ENABLE_PLACEHOLDERS)
            .build()).build()


    fun getObservableMovies(): LiveData<PagedList<MovieItem>> {
        return allMyMovieItem
    }


    class Factory(val application: Application, val repo: Repo) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MyVideoViewModel(application, repo) as T
        }
    }

}