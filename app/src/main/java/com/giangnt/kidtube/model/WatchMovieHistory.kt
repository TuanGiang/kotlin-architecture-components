package com.giangnt.kidtube.model

import android.arch.persistence.room.*
import java.util.*

@Entity(tableName = "WatchMovieHistory", foreignKeys = (arrayOf(ForeignKey(
        entity = Movie::class,
        onUpdate = ForeignKey.CASCADE,
        parentColumns = arrayOf("videoId"),
        childColumns = arrayOf("videoId"))))
)
data class WatchMovieHistory(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int,

        @ColumnInfo(name = "videoId")
        val videoId: String,

        @ColumnInfo(name = "time")
        val time: Date
)