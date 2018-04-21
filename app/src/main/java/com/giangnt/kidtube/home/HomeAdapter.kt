package com.giangnt.kidtube.home

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.giangnt.kidtube.R
import com.giangnt.kidtube.databinding.ItemMovieBinding
import com.giangnt.kidtube.model.MovieItem

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 1:14 PM - 4/17/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.base - HomeAdapter
 */
class HomeAdapter(val callback: HomeClickCallback) : PagedListAdapter<MovieItem, HomeAdapter.MovieHolder>(diffCallback) {


    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.binding.movieItem = getItem(position)
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie,
                parent, false)
        binding.callBack = callback
        return MovieHolder(binding)
    }

    class MovieHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         * <p>
         * When you add a Cheese with the 'Add' button, the PagedListAdapter uses diffCallback to
         * detect there's only a single item difference from before, so it only needs to animate and
         * rebind a single view.
         *
         * @see android.support.v7.util.DiffUtil
         */
        private val diffCallback = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                    oldItem.video.videoId == newItem.video.videoId

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                    oldItem == newItem
        }
    }

}