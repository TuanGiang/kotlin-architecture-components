package com.giangnt.kidtube.account

import android.arch.lifecycle.MutableLiveData
import com.giangnt.kidtube.model.User

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 9:56 AM - 4/17/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.account - AccountManager
 */
class AccountManager {
    companion object {
        val userInfo = MutableLiveData<User>()

        fun intUserInfo() {
            userInfo.value = null
        }

        fun setUser(user: User) {
            userInfo.postValue(user)
        }

    }
}