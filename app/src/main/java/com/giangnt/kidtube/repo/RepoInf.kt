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
    companion object {
        public fun getHome(pageIndex: Int, pageSize: Int = 12): List<MovieItem> {
            val movies = ArrayList<MovieItem>()
            for (i in 0..pageSize) {
                val id = pageIndex.toString() + " " + i.toString()
                val movieDetail = Movie(id, "", "", "Title : " + id, "Description : " + id, "", ""
                        , "", "", " Channel ID : " + id, " Channel Title : " + id, id, "Duration: " + id, "" +
                        "", "", pageIndex * pageSize + i, pageIndex * pageSize, pageIndex)
                val channelDetail = Channel(id, "", "", "Title : " + id, "Description : " + id, "", ""
                        , "")

                val movieItem = MovieItem(movieDetail, channelDetail)
                movies.add(movieItem)
            }
            return movies
        }
    }

}