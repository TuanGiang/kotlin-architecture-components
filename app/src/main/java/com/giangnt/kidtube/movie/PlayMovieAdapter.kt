package com.giangnt.kidtube.movie

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giangnt.kidtube.R
import com.giangnt.kidtube.databinding.ItemMovieInfoBinding
import com.giangnt.kidtube.databinding.ItemMovieSuggestBinding
import com.giangnt.kidtube.model.MovieItem

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 10:45 AM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.movie - PlayMovieAdapter
 */
class PlayMovieAdapter(val callback: PlayMovieClickCallback) : Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_INFO = 0
    private val TYPE_SUGGEST_HEADER = 1
    private val TYPE_SUGGEST = 2

    var selectedItem: MovieItem? = null
    var items: List<MovieItem>? = null

    fun setData(item: MovieItem, list: List<MovieItem>?) {
        this.selectedItem = item
        items = list
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieInfoViewHolder -> {
                holder.binding.movieItem = selectedItem
                holder.binding.executePendingBindings()
            }
            is MovieSuggestHeaderHolder -> {
                //do nothing
            }
            is MovieSuggestHolder -> {
                holder.binding.movieItem = items!![position - 2]
                holder.binding.callBack = callback
                holder.binding.executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_INFO -> {
                val binding: ItemMovieInfoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie_info,
                        parent, false)
                MovieInfoViewHolder(binding)
            }
            TYPE_SUGGEST_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_movie_suggest_header, parent, false)
                MovieSuggestHeaderHolder(view)
            }
            else -> {
                val binding: ItemMovieSuggestBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie_suggest,
                        parent, false)
                MovieSuggestHolder(binding)
            }

        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_INFO
            1 -> TYPE_SUGGEST_HEADER
            else -> TYPE_SUGGEST
        }
    }

    override fun getItemCount(): Int {
        var count = 0
        if (selectedItem != null)
            count += 1
        if (items != null) {
            if (items!!.size > 0) {
                count += 1 + items!!.size
            }
        }
        return count
    }

    class MovieInfoViewHolder(val binding: ItemMovieInfoBinding) : RecyclerView.ViewHolder(binding.root)

    class MovieSuggestHeaderHolder(view: View) : RecyclerView.ViewHolder(view)

    class MovieSuggestHolder(val binding: ItemMovieSuggestBinding) : RecyclerView.ViewHolder(binding.root)
}