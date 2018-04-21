package com.giangnt.kidtube.channel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.giangnt.kidtube.base.viewmodel.LoginViewModel
import com.giangnt.kidtube.model.Channel
import com.giangnt.kidtube.repo.Repo
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


class ChannelListViewModel(application: Application, val repo: Repo) : LoginViewModel(application) {

    val channels = MutableLiveData<List<Channel>>()
    private val mDisposable = CompositeDisposable()

    init {
        mDisposable.add(repo.getChannelList(application)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({channels.postValue(it)}))

    }


    fun getObservableChannels(): MutableLiveData<List<Channel>> {
        return channels
    }

    override fun onCleared() {
        mDisposable.clear()

        super.onCleared()
    }

    class Factory(val application: Application, val repo: Repo) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ChannelListViewModel(application, repo) as T
        }
    }


}