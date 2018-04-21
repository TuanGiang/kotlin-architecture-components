package com.giangnt.kidtube.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.model.Playlist

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 5:01 PM - 4/19/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.dao - PlaylistDAO
 */
@Dao
interface PlaylistDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(playlist: ArrayList<Playlist>)

    @Query("SELECT * FROM Playlist")
    fun getAll(): List<Playlist>

    @Query("SELECT * FROM Playlist where channelId == :channelId ")
    fun getPlaylistByChannel(channelId: String) : DataSource.Factory<Int, Playlist>
}