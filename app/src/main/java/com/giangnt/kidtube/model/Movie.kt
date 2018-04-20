package com.giangnt.kidtube.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Movie", foreignKeys = (arrayOf(ForeignKey(
        entity = Channel::class,
        onUpdate = ForeignKey.CASCADE,
        parentColumns = arrayOf("channelId"),
        childColumns = arrayOf("videoChannelId"))))
)
data class Movie(

        @PrimaryKey
        @ColumnInfo(name = "videoId")
        @SerializedName("videoId")
        val videoId: String,

        @ColumnInfo(name = "videoPublicAt")
        @SerializedName("publicAt")
        val publicAt: String,

        @ColumnInfo(name = "videoThumbnail")
        @SerializedName("thumbnail")
        val thumbnail: String,

        @ColumnInfo(name = "videoTitle")
        @SerializedName("title")
        val title: String,

        @ColumnInfo(name = "videoDescription")
        @SerializedName("description")
        val description: String,

        @ColumnInfo(name = "videoSource")
        @SerializedName("source")
        val source: String,

        @ColumnInfo(name = "videoCreatedAt")
        @SerializedName("createdAt")
        val createdAt: String,


        @ColumnInfo(name = "videoUpdatedAt")
        @SerializedName("updatedAt")
        val updatedAt: String,

        @ColumnInfo(name = "videoPlaylistId")
        @SerializedName("playlistId")
        val playlistId: String,


        @ColumnInfo(name = "videoChannelId")
        @SerializedName("channelId")
        val channelId: String,

        @ColumnInfo(name = "videoChannelTitle")
        @SerializedName("channelTitle")
        val channelTitle: String,

        @ColumnInfo(name = "videoDuration")
        @SerializedName("duration")
        val duration: String,

        @ColumnInfo(name = "playListPosition")
        @SerializedName("position")
        val playListPosition: Int,

        @ColumnInfo(name = "dimension")
        @SerializedName("dimension")
        val dimension: String,

        @ColumnInfo(name = "definition")
        @SerializedName("definition")
        val definition: String,

        @ColumnInfo(name = "viewCount")
        @SerializedName("viewCount")
        val viewCount: Int,

        @ColumnInfo(name = "likeCount")
        @SerializedName("likeCount")
        val likeCount: Int,

        @ColumnInfo(name = "dislikeCount")
        @SerializedName("dislikeCount")
        val dislikeCount: Int

) : Serializable