package com.giangnt.kidtube.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.giangnt.kidtube.model.WatchMovieHistory

@Dao
interface WatchHistoryDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movies: List<WatchMovieHistory>)

    @Query("SELECT * FROM WatchMovieHistory")
    fun getAll(): List<WatchMovieHistory>
}
