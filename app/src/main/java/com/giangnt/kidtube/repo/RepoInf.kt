package com.giangnt.kidtube.repo

import android.content.Context
import com.giangnt.kidtube.entity.AppDatabase
import com.giangnt.kidtube.model.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 9:54 AM - 4/16/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.repo - RepoInf
 */
public class Repo {

    fun saveChannels(context: Context, channels: ArrayList<Channel>): Deferred<Any> {
        return async(CommonPool) {
            AppDatabase.getInstance(context).channelDao().insertAll(channels)
        }
    }

    fun getChannels(context: Context): Deferred<List<Channel>> {
        return async(CommonPool) {
            AppDatabase.getInstance(context).channelDao().getAll()
        }
    }


    fun getHome(pageIndex: Int, pageSize: Int = 12): ArrayList<MovieItem> {
        return Repo.getHome(pageIndex, pageSize)
    }

    fun getChannelList(pageIndex: Int, pageSize: Int = 12): ArrayList<ChannelItem> {
        return Repo.getChannelList(pageIndex, pageSize)
    }

    public fun getRelated(videoId: String): ArrayList<MovieItem> {
        return Repo.getRelated(videoId)
    }

    public fun getPlaylist(channelId: String): ArrayList<Playlist> {
        return Repo.getPlaylist(channelId)
    }

    companion object {
        public fun getHome(pageIndex: Int, pageSize: Int = 12): ArrayList<MovieItem> {
            val movies = ArrayList<MovieItem>()
            for (i in 0..pageSize) {
                val id = "Index: " + pageIndex.toString() + " Position: " + i.toString()
                val movieDetail = Movie(id, "", "https://www.w3schools.com/howto/img_fjords.jpg", "Movie Title : " + id, "Description : " + id, "", ""
                        , "", "", " Channel ID : " + id, " Channel Title : " + id, id, "Duration: " + id, "" +
                        "", "", pageIndex * pageSize + i, pageIndex * pageSize, pageIndex)
                val channelDetail = Channel(id, "", "https://www.jqueryscript.net/images/Simplest-Responsive-jQuery-Image-Lightbox-Plugin-simple-lightbox.jpg", "Channel Title : " + id, "Description : " + id, "", ""
                        , "")

                val movieItem = MovieItem(movieDetail, channelDetail)
                movies.add(movieItem)
            }
            return movies
        }

        public fun getRelated(videoId: String): ArrayList<MovieItem> {
            val movies = ArrayList<MovieItem>()
            for (i in 0..10) {
                val id = "Index: " + i.toString()
                val movieDetail = Movie(id, "", "https://www.w3schools.com/howto/img_fjords.jpg", "Movie Title : " + id, "Description : " + id, "", ""
                        , "", "", " Channel ID : " + id, " Channel Title : " + id, id, "Duration: " + id, "" +
                        "", "", 12 + i, 12, 12)
                val channelDetail = Channel(id, "", "https://www.jqueryscript.net/images/Simplest-Responsive-jQuery-Image-Lightbox-Plugin-simple-lightbox.jpg", "Channel Title : " + id, "Description : " + id, "", ""
                        , "")

                val movieItem = MovieItem(movieDetail, channelDetail)
                movies.add(movieItem)
            }
            return movies
        }

        public fun getChannelList(pageIndex: Int, pageSize: Int = 12): ArrayList<ChannelItem> {
            val channels = ArrayList<ChannelItem>()
            for (i in 0..pageSize) {
                val id = "Index: " + pageIndex.toString() + " Position: " + i.toString()
                val channelDetail = Channel(id, "", "https://www.jqueryscript.net/images/Simplest-Responsive-jQuery-Image-Lightbox-Plugin-simple-lightbox.jpg", "Channel Title : " + id, "Description : " + id, "", ""
                        , "")

                val channelStatistic = ChannelStatistic(id, i * 1000, i * 10)

                val channelItem = ChannelItem(channelDetail, channelStatistic)
                channels.add(channelItem)
            }
            return channels
        }

        public fun getPlaylist(channelId: String): ArrayList<Playlist> {
            val playlists = ArrayList<Playlist>()
            for (i in 0..10) {
                val id = "Index: " + channelId + " Position: " + i.toString()
                val playlist = Playlist(id, "", "https://www.w3schools.com/howto/img_fjords.jpg", "Movie Title : " + id, "Description : " + id, "", ""
                        , "", i * 10, " Channel ID : " + id, " Channel Title : " + id)

                playlists.add(playlist)
            }
            return playlists
        }
    }

}