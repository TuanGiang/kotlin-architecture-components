package com.giangnt.kidtube.search

import android.app.Application
import android.arch.lifecycle.*
import android.databinding.ObservableField
import android.text.TextUtils
import android.widget.Toast
import com.giangnt.kidtube.model.Channel
import com.giangnt.kidtube.model.Playlist
import com.giangnt.kidtube.net.Youtube
import com.giangnt.kidtube.net.playlist.item.PlaylistItem
import com.giangnt.kidtube.repo.Repo
import com.giangnt.kidtube.toMovie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.launch

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 9:39 AM - 4/19/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.search - SearchChannelViewModel
 */
class SearchChannelViewModel(application: Application, val repo: Repo) : AndroidViewModel(application) {

    val isShowSelected = ObservableField<Boolean>()

    val searchResult = MutableLiveData<ArrayList<Channel>>()

    val downloadDone = MutableLiveData<Boolean>()

    val channelSelected = MutableLiveData<ArrayList<Channel>>()

    init {
        isShowSelected.set(false)
    }

    fun getObservableResult(): LiveData<ArrayList<Channel>> {
        return searchResult
    }

    fun getObservableDownloadDone(): LiveData<Boolean> {
        return downloadDone
    }


    fun getObservableSelected(): LiveData<ArrayList<Channel>> {
        return channelSelected
    }

    fun searchChannel(query: String) {
        Youtube.getYoutubeService().search(query = query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val list = it.items.filter { it.id.kind == "youtube#channel" }
                    val listChannel = ArrayList<Channel>()
                    list.mapTo(listChannel) { it.toChannel() }
                    searchResult.postValue(listChannel)
                }, { it.printStackTrace() })


    }

    fun addSelected(channel: Channel) {
        isShowSelected.set(true)
        if (channelSelected.value == null) {
            val list = ArrayList<Channel>()
            list.add(channel)
            channelSelected.postValue(list)
        } else {
            val list = channelSelected.value
            if (list!!.contains(channel)) {
                Toast.makeText(getApplication(), "Channel : " + channel.title + " is already exist!", Toast.LENGTH_SHORT).show()
            } else {
                list!!.add(channel)
                channelSelected.postValue(list)
            }
        }
    }

    class Factory(val application: Application, val repo: Repo) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SearchChannelViewModel(application, repo) as T
        }
    }

    fun saveChannel() {
        launch {
            repo.saveChannels(getApplication(), channelSelected.value!!).await()
            val channels = repo.getChannels(getApplication()).await()
            fetchPlaylist(channels)
        }
    }

    private fun onDone() {
        downloadDone.postValue(true)
    }

    private fun fetchPlaylist(channels: List<Channel>) {
        launch {
            channels.forEach {
                var existLoadMore = true
                var pageToken: String? = null
                while (existLoadMore) {
                    val playlistData = repo.fetchPlayLists(it.channelId, pageToken).await()
                    if (playlistData != null) {
                        val playlists = ArrayList<Playlist>()
                        playlistData.items.forEach {
                            playlists.add(it.toPlayList())
                        }
                        repo.savePlayLists(getApplication(), playlists)
                        if (!TextUtils.isEmpty(playlistData.nextPageToken)) {
                            pageToken = playlistData.nextPageToken
                        } else {
                            existLoadMore = false
                        }
                    }
                }
            }
            val playlists = repo.getPlayLists(context = getApplication()).await()
            fetchPlaylistItem(playlists)
        }
    }

    private fun fetchPlaylistItem(playlists: List<Playlist>) {
        launch {
            playlists.forEach {
                var existLoadMore = true
                var pageToken: String? = null
                while (existLoadMore) {
                    val itemData = repo.fetchPlayListItems(it.id, pageToken).await()
                    if (itemData != null) {
                        val items = ArrayList<PlaylistItem>()
                        itemData.items
                                .filter { it.snippet.resourceId.kind == "youtube#video" }
                                .forEach {
                                    items.add(it.toItem())
                                }

                        val itemIds = ArrayList<String>()
                        items.forEach { itemIds.add(it.videoId) }
                        val movieItems = repo.fetchMovies(itemIds.joinToString()).await()
                        if (movieItems != null) {
                            val movies = movieItems.items.map { Pair(it, getItemById(items, it.id)) }.map { it.toMovie() }
                            repo.saveMovies(getApplication(), movies)
                        }

                        if (!TextUtils.isEmpty(itemData.nextPageToken)) {
                            pageToken = itemData.nextPageToken
                        } else {
                            existLoadMore = false
                        }
                    }
                }
            }
            onDone()
        }
    }

    fun getItemById(list: List<PlaylistItem>, id: String): PlaylistItem {
        val item = list.find { it.videoId == id }
        if (item != null) {
            return item
        }
        return PlaylistItem("", 0, "")
    }

    fun save() {
        saveChannel()
    }


}



