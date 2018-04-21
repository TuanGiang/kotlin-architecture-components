package com.giangnt.kidtube.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.giangnt.kidtube.model.Channel
import io.reactivex.Flowable

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 3:32 PM - 4/19/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.dao - ChannelDAO
 */

@Dao
interface ChannelDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(channels: ArrayList<Channel>)

    @Query("SELECT * FROM Channel")
    fun getAll(): List<Channel>

    @Query("SELECT * FROM Channel")
    fun getAllChannels(): Flowable<List<Channel>>

}