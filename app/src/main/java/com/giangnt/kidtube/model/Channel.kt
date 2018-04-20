package com.giangnt.kidtube.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Channel")
data class Channel(

        @PrimaryKey
        @ColumnInfo(name = "channelId")
        @SerializedName("channelId")
        val channelId: String,

        @ColumnInfo(name = "channelPublicAt")
        @SerializedName("publicAt")
        val publicAt: String,

        @ColumnInfo(name = "channelThumbnail")
        @SerializedName("thumbnail")
        val thumbnail: String,

        @ColumnInfo(name = "channelTitle")
        @SerializedName("title")
        val title: String,

        @ColumnInfo(name = "channelDescription")
        @SerializedName("description")
        val description: String,

        @ColumnInfo(name = "channelSource")
        @SerializedName("source")
        val source: String,

        @ColumnInfo(name = "channelCreatedAt")
        @SerializedName("createdAt")
        val createdAt: String,

        @ColumnInfo(name = "channelUpdatedAt")
        @SerializedName("updatedAt")
        val updatedAt: String

) : Serializable