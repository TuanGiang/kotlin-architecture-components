package com.giangnt.kidtube.repo

import android.content.Context
import com.giangnt.kidtube.entity.AppDatabase
import com.giangnt.kidtube.model.*
import com.giangnt.kidtube.net.Youtube
import com.giangnt.kidtube.net.movie.MovieResponse
import com.giangnt.kidtube.net.playlist.PlaylistResponse
import com.giangnt.kidtube.net.playlist.item.PlaylistItemResponse
import io.reactivex.Flowable
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

    fun saveMyVideos(context: Context, arrayOf: List<MyVideo>): Deferred<Any> {
        return async(CommonPool) {
            AppDatabase.getInstance(context).myVideoDao().insertAll(arrayOf)
        }
    }

    fun saveChannels(context: Context, channels: ArrayList<Channel>): Deferred<Any> {
        return async(CommonPool) {
            AppDatabase.getInstance(context).channelDao().insertAll(channels)
        }
    }

    fun savePlayLists(context: Context, playlists: ArrayList<Playlist>): Deferred<Any> {
        return async(CommonPool) {
            AppDatabase.getInstance(context).playlistDao().insertAll(playlists)
        }
    }

    fun saveMovies(context: Context, movies: List<Movie>): Deferred<Any> {
        return async(CommonPool) {
            AppDatabase.getInstance(context).movieDao().insertAll(movies)
        }
    }


    fun getChannels(context: Context): Deferred<List<Channel>> {
        return async(CommonPool) {
            AppDatabase.getInstance(context).channelDao().getAll()
        }
    }

    fun getPlayLists(context: Context): Deferred<List<Playlist>> {
        return async(CommonPool) {
            AppDatabase.getInstance(context).playlistDao().getAll()
        }
    }

    fun getMyVideos(context: Context): Deferred<List<MyVideo>> {
        return async(CommonPool) {
            AppDatabase.getInstance(context).myVideoDao().getAll()
        }
    }

    fun getMovieIdByPlayList(context: Context, playlistId: String): Deferred<List<String>> {
        return async(CommonPool) {
            AppDatabase.getInstance(context).movieDao().getMovieIdByPlaylist(playlistId)
        }
    }

    fun getMovies(context: Context): Deferred<List<Movie>> {
        return async(CommonPool) {
            AppDatabase.getInstance(context).movieDao().getAll()
        }
    }


    fun fetchPlayLists(channelId: String, pageToken: String?): Deferred<PlaylistResponse?> {
        return async(CommonPool) {
            Youtube.getYoutubeService().getPlaylist(channelId = channelId, pageToken = pageToken).execute().body()
        }
    }

    fun fetchPlayListItems(playlistId: String, pageToken: String?): Deferred<PlaylistItemResponse?> {
        return async(CommonPool) {
            Youtube.getYoutubeService().getPlaylistItems(playlistId = playlistId, pageToken = pageToken).execute().body()
        }
    }

    fun fetchMovies(ids: String): Deferred<MovieResponse?> {
        return async(CommonPool) {
            Youtube.getYoutubeService().getMovies(ids = ids).execute().body()
        }
    }

    fun getChannelList(context: Context): Flowable<List<Channel>> {
        return AppDatabase.getInstance(context).channelDao().getAllChannels()
    }

    fun getHomeVideoRelated(context: Context, playlistId : String, videoId: String): Flowable<List<MovieItem>>  {
        return AppDatabase.getInstance(context).movieDao().getHomeVideoRelated(playlistId, videoId)
    }

    fun getChannelVideoRelated(context: Context, channelId : String, videoId: String): Flowable<List<MovieItem>>  {
        return AppDatabase.getInstance(context).movieDao().getChannelVideoRelated(channelId, videoId)
    }

    fun getPlaylistVideoRelated(context: Context, playlistId : String, videoId: String): Flowable<List<MovieItem>>  {
        return AppDatabase.getInstance(context).movieDao().getPlaylistVideoRelated(playlistId, videoId)
    }

    fun getMyVideoRelated(context: Context, videoId: String): Flowable<List<MovieItem>>  {
        return AppDatabase.getInstance(context).movieDao().getMyVideoRelated(videoId)
    }

    fun getFirstPlaylistVideo(context: Context, playlistId: String): Deferred<MovieItem> {
        return async(CommonPool) {
            AppDatabase.getInstance(context).movieDao().getFirstPlaylistVideo(playlistId)
        }
    }


//    fun getHomeData(context: Context): Deferred<List<MovieItem>> {
//        return async(CommonPool) {
//            AppDatabase.getInstance(context).movieDao().getAllWithChannel()
//        }
//    }

    fun getHome(pageIndex: Int, pageSize: Int = 12): ArrayList<MovieItem> {
        return Repo.getHome(pageIndex)
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
            return movies
        }

        public fun getRelated(videoId: String): ArrayList<MovieItem> {
            val movies = ArrayList<MovieItem>()
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