package com.giangnt.kidtube.net

import com.giangnt.kidtube.BuildConfig
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 1:10 PM - 4/19/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube - YoutubeService
 */
interface YoutubeService {
    @GET("youtube/v3/search/")
    fun search(@Query("part") part: String = "id,snippet", @Query("maxResults") maxResults: Int = 20,
               @Query("q") query: String, @Query("key") key: String = BuildConfig.API_KEY): Single<SearchResponse>


}