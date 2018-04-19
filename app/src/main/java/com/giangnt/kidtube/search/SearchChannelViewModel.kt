package com.giangnt.kidtube.search

import android.app.Application
import android.arch.lifecycle.*
import android.databinding.ObservableField
import android.util.Log
import android.widget.Toast
import com.giangnt.kidtube.entity.AppDatabase
import com.giangnt.kidtube.model.Channel
import com.giangnt.kidtube.net.Youtube
import com.giangnt.kidtube.repo.Repo
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

    val channelSelected = MutableLiveData<ArrayList<Channel>>()

    init {
        isShowSelected.set(false)
    }

    fun getObservableResult(): LiveData<ArrayList<Channel>> {
        return searchResult
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



    fun save() {
        launch {
            channelSelected.value.let {
                repo.saveChannels(getApplication(), it!!).await()
            }

            repo.getChannels(getApplication()).await()

        }

    }


}



