package com.giangnt.kidtube.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 1:35 PM - 4/19/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.net - Youtube
 */
class Youtube {
    companion object {


        private fun createRetrofit(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttpClient())
                    .build()
        }
        fun getYoutubeService() : YoutubeService{
            return createRetrofit().create(YoutubeService::class.java)
        }
    }
}