package com.giangnt.kidtube.model

import java.io.Serializable

data class MovieItem(
        val video: Movie,
        val channel: Channel
) : Serializable