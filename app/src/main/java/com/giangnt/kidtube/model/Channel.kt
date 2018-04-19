package com.giangnt.kidtube.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Channel")
data class Channel(
        @PrimaryKey
        @ColumnInfo(name = "id")
        @SerializedName("id")
        val id: String,

        @ColumnInfo(name = "publicAt")
        @SerializedName("publicAt")
        val publicAt: String,

        @ColumnInfo(name = "thumbnail")
        @SerializedName("thumbnail")
        val thumbnail: String,

        @ColumnInfo(name = "title")
        @SerializedName("title")
        val title: String,

        @ColumnInfo(name = "description")
        @SerializedName("description")
        val description: String,

        @ColumnInfo(name = "source")
        @SerializedName("source")
        val source: String,

        @ColumnInfo(name = "createdAt")
        @SerializedName("createdAt")
        val createdAt: String,

        @ColumnInfo(name = "updatedAt")
        @SerializedName("updatedAt")
        val updatedAt: String

) : Serializable