package com.giangnt.kidtube.base.viewmodel


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.giangnt.kidtube.account.AccountManager
import com.giangnt.kidtube.model.User

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 10:44 AM - 4/16/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.base.viewmodel - LoginViewModel
 */
public abstract class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val userLogin: MutableLiveData<User>

    public val user = ObservableField<User>()

    init {
        userLogin = AccountManager.userInfo
    }

    public fun getObservableUser(): LiveData<User> {
        return userLogin
    }

    public fun setUser(userData: User?) {
        user.set(userData)
    }
}