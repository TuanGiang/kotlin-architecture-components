package com.giangnt.kidtube.model


import android.arch.persistence.room.Embedded
import java.io.Serializable


data class MovieItem(
        @Embedded val video: Movie,
        @Embedded val channel: Channel
) : Serializable
