package com.giangnt.kidtube.model

import android.arch.persistence.room.*

@Entity(tableName = "MyVideo", foreignKeys = (arrayOf(ForeignKey(
        entity = Movie::class,
        onUpdate = ForeignKey.CASCADE,
        parentColumns = arrayOf("videoId"),
        childColumns = arrayOf("myVideoId"))))
)
data class MyVideo(

        @PrimaryKey
        @ColumnInfo(name = "myVideoId")
        val videoId: String
)