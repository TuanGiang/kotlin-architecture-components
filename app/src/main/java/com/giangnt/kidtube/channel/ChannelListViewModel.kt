package com.giangnt.kidtube.channel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.giangnt.kidtube.base.viewmodel.LoginViewModel
import com.giangnt.kidtube.model.ChannelItem
import com.giangnt.kidtube.repo.Repo

class ChannelListViewModel(application: Application, val repo: Repo) : LoginViewModel(application) {
    val channels = MutableLiveData<ArrayList<ChannelItem>>()
    var pageIndex = 0

    init {
        val firstData = repo.getChannelList(pageIndex)
        channels.postValue(firstData)
    }

    fun getObservableChannels(): MutableLiveData<ArrayList<ChannelItem>> {
        return channels
    }

    fun getMoreChannel() {
        pageIndex++
        val items = repo.getChannelList(pageIndex)
        val currentItems = channels.value
        currentItems!!.addAll(items)
        channels.postValue(currentItems)
    }

    class Factory(val application: Application, val repo: Repo) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ChannelListViewModel(application, repo) as T
        }
    }


}