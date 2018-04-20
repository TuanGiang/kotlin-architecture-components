package com.giangnt.kidtube.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.giangnt.kidtube.model.Movie
import com.giangnt.kidtube.model.MovieItem

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 10:24 AM - 4/20/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.dao - MovieDAO
 */
@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM Movie")
    fun getAll(): List<Movie>

    @Query("select * from Movie, Channel where Movie.videoChannelId = Channel.channelId")
    fun getAllWithChannel(): List<MovieItem>

}