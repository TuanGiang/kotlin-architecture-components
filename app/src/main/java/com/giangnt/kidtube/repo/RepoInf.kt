package com.giangnt.kidtube.repo

import com.giangnt.kidtube.model.Channel
import com.giangnt.kidtube.model.Movie
import com.giangnt.kidtube.model.MovieItem

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

    fun getHome(pageIndex: Int, pageSize: Int = 12): ArrayList<MovieItem> {
        return Repo.getHome(pageIndex, pageSize)
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
    }

}