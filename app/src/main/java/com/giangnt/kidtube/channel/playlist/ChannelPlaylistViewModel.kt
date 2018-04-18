package com.giangnt.kidtube.channel.playlist

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.giangnt.kidtube.base.viewmodel.LoginViewModel
import com.giangnt.kidtube.model.Playlist
import com.giangnt.kidtube.repo.Repo

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 4:33 PM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.channel.playlist - ChannelPlaylistViewModel
 */
class ChannelPlaylistViewModel(application: Application, var repo: Repo, val channelId: String) : LoginViewModel(application) {

    val movieLiveData = MutableLiveData<ArrayList<Playlist>>()

    init {
        movieLiveData.postValue(repo.getPlaylist(channelId))
    }

    fun getObservableMovies(): LiveData<ArrayList<Playlist>> {
        return movieLiveData
    }


    class Factory(val application: Application, val repo: Repo, val channelId: String) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ChannelPlaylistViewModel(application, repo, channelId) as T
        }
    }

}