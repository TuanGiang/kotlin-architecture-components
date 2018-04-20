package com.giangnt.kidtube

import com.giangnt.kidtube.model.Movie
import com.giangnt.kidtube.net.movie.MovieData
import com.giangnt.kidtube.net.playlist.item.PlaylistItem

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 1:54 PM - 4/20/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube - Extension
 */
fun Pair<MovieData, PlaylistItem>.toMovie(): Movie {
    return Movie(first.id, first.snippet.publishedAt, first.snippet.thumbnail.medium.url,
            first.snippet.title, first.snippet.description, first.kind, "", "", second.playlistId,
            first.snippet.channelId, first.snippet.channelTitle, first.contentDetails.duration, second.position, first.contentDetails.dimension, first.contentDetails.definition, first.statistics.viewCount, first.statistics.likeCount, first.statistics.dislikeCount)
}

//                                Movie(it.first.id, it.first.snippet.publishedAt, it.first.snippet.thumbnail.medium.url,
//                                        it.first.snippet.title, it.first.snippet.description, it.first.kind, "", "", it.second.playlistId,
//                                        it.first.snippet.channelId, it.first.snippet.channelTitle, it.first.contentDetails.duration, it.second.position, it.first.contentDetails.dimension, it.first.contentDetails.definition, it.first.statistics.viewCount, it.first.statistics.likeCount, it.first.statistics.dislikeCount)
