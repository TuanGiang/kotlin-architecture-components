package com.giangnt.kidtube.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.giangnt.kidtube.model.MyVideo

@Dao
interface MyVideoDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movies: List<MyVideo>)

    @Query("SELECT * FROM MyVideo")
    fun getAll(): List<MyVideo>
}