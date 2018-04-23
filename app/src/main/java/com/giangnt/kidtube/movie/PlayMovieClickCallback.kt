package com.giangnt.kidtube.movie

import com.giangnt.kidtube.model.MovieItem

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 11:10 AM - 4/23/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.movie - PlayMovieClickCallback
 */
interface PlayMovieClickCallback {
    fun onClick(movieItem: MovieItem)
}