package com.giangnt.kidtube.home

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.giangnt.kidtube.base.viewmodel.LoginViewModel
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.repo.Repo
import kotlinx.coroutines.experimental.launch

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 11:00 AM - 4/16/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.home - HomeViewModel
 */
class HomeViewModel(application: Application, var repo: Repo) : LoginViewModel(application) {

    val movieLiveData = MutableLiveData<ArrayList<MovieItem>>()

    var pageIndex = 0

//    init {
//        movieLiveData.postValue(repo.getHome(pageIndex))
//    }
    init {
        getHome()
    }

    fun getHome() {
        launch {
            val firstData = repo.getHomeData(getApplication(), 0).await()
            movieLiveData.postValue(firstData as ArrayList<MovieItem>?)
        }
    }

    fun getObservableMovies(): LiveData<ArrayList<MovieItem>> {
        return movieLiveData
    }

    fun getMoreHome() {
        pageIndex++
        val items = repo.getHome(pageIndex)
        val currentItems = movieLiveData.value
        currentItems!!.addAll(items)
        movieLiveData.postValue(currentItems)
    }


    class Factory(val application: Application, val repo: Repo) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(application, repo) as T
        }
    }

}