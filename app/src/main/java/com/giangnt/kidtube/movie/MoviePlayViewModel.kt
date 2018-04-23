package com.giangnt.kidtube.movie

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.giangnt.kidtube.base.viewmodel.BaseViewModel
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.repo.Repo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 9:13 AM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.movie - MoviePlayViewModel
 */
class MoviePlayViewModel(application: Application, val repo: Repo, val movie: MovieItem, val from: String) : BaseViewModel(application) {

    val movieItems = MutableLiveData<List<MovieItem>>()
    val movieItem = MutableLiveData<MovieItem>()
    val mDisposable = CompositeDisposable()

    init {
        movieItem.postValue(movie)
        when (from) {
            MoviePlayActivity.FROM_HOME -> {
                getHomeVideoRelated(movie.video.playlistId, movie.video.videoId)
            }
            MoviePlayActivity.FROM_CHANNEL -> {
                getChannelVideoRelated(movie.video.channelId, movie.video.videoId)
            }
            MoviePlayActivity.FROM_MY_VIDEO -> {
                getMyVideoRelated(movie.video.videoId)
            }
            MoviePlayActivity.FROM_PLAYLIST -> {
                getPlaylistVideoRelated(movie.video.playlistId, movie.video.videoId)
            }

        }
    }

    private fun getPlaylistVideoRelated(playlistId: String, videoId: String) {
        mDisposable.add(repo.getPlaylistVideoRelated(getApplication(), playlistId, videoId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieItems.postValue(it) }))
    }

    private fun getMyVideoRelated(videoId: String) {
        mDisposable.add(repo.getMyVideoRelated(getApplication(), videoId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieItems.postValue(it) }))
    }

    private fun getChannelVideoRelated(channelId: String, videoId: String) {
        mDisposable.add(repo.getChannelVideoRelated(getApplication(), channelId, videoId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieItems.postValue(it) }))
    }

    private fun getHomeVideoRelated(playlistId: String, videoId: String) {
        mDisposable.add(repo.getHomeVideoRelated(getApplication(), playlistId, videoId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieItems.postValue(it) }))
    }

    override fun onCleared() {
        mDisposable.clear()

        super.onCleared()
    }

    fun setMovie(movie: MovieItem) {
        val current = movieItem.value!!

        val suggestList = ArrayList<MovieItem>()
        suggestList.addAll(movieItems.value!!.filter { it.video.videoId != movie.video.videoId })
        suggestList.add(current)

        movieItem.postValue(movie)
        movieItems.postValue(suggestList)

    }

    public fun getObservableMovies(): MutableLiveData<List<MovieItem>> {
        return movieItems
    }

    public fun getObservableMovie(): MutableLiveData<MovieItem> {
        return movieItem
    }


    class Factory(val application: Application, val repo: Repo, val movieItem: MovieItem, val from: String) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MoviePlayViewModel(application, repo, movieItem, from) as T
        }
    }


}