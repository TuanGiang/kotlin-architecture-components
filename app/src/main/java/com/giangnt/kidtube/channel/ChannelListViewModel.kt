package com.giangnt.kidtube.channel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.giangnt.kidtube.base.viewmodel.BaseViewModel
import com.giangnt.kidtube.model.Channel
import com.giangnt.kidtube.repo.Repo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class ChannelListViewModel(application: Application, val repo: Repo) : BaseViewModel(application) {

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