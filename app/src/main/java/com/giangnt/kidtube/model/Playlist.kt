package com.giangnt.kidtube.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Playlist")
data class Playlist(

        @PrimaryKey
        @SerializedName("id")
        @ColumnInfo(name = "id")
        val id: String,

        @SerializedName("publicAt")
        @ColumnInfo(name = "publicAt")
        val publicAt: String,

        @SerializedName("thumbnail")
        @ColumnInfo(name = "thumbnail")
        val thumbnail: String,

        @SerializedName("title")
        @ColumnInfo(name = "title")
        val title: String,

        @SerializedName("description")
        @ColumnInfo(name = "description")
        val description: String,

        @SerializedName("source")
        @ColumnInfo(name = "source")
        val source: String,

        @SerializedName("createdAt")
        @ColumnInfo(name = "createdAt")
        val createdAt: String,

        @SerializedName("updatedAt")
        @ColumnInfo(name = "updatedAt")
        val updatedAt: String,

        @SerializedName("itemCount")
        @ColumnInfo(name = "itemCount")
        val itemCount: Int,

        @SerializedName("channelId")
        @ColumnInfo(name = "channelId")
        val channelId: String,

        @SerializedName("channelTitle")
        @ColumnInfo(name = "channelTitle")
        val channelTitle: String
) : Serializable