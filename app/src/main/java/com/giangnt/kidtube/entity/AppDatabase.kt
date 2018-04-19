package com.giangnt.kidtube.entity

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.giangnt.kidtube.dao.ChannelDAO
import com.giangnt.kidtube.dao.PlaylistDAO
import com.giangnt.kidtube.model.Channel
import com.giangnt.kidtube.model.Playlist

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 3:35 PM - 4/19/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.entity - AppDatabase
 */
@Database(entities = arrayOf(Channel::class, Playlist::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun channelDao(): ChannelDAO

    abstract fun playlistDao(): PlaylistDAO

    companion object {
        val DB_NAME = "MYDB.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, DB_NAME)
                        .build()
    }
}