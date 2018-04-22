package com.giangnt.kidtube.model

import android.arch.persistence.room.Embedded

data class MyMovieItem(
        @Embedded val video: Movie,
        @Embedded val channel: Channel,
        @Embedded val myVideo: MyVideo
)