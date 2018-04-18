package com.giangnt.kidtube.channel.movies

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.giangnt.kidtube.R
import com.giangnt.kidtube.databinding.ItemChannelMovieBinding
import com.giangnt.kidtube.model.MovieItem

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 4:09 PM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.channel.movies - ChannelMovieAdapter
 */
class ChannelMovieAdapter(val callback: ChannelMovieCallback) : RecyclerView.Adapter<ChannelMovieAdapter.MovieHolder>() {

    var items = ArrayList<MovieItem>()

    fun setList(list: ArrayList<MovieItem>) {
        items = list
        this.notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        if (items == null) {
            return 0
        }
        return items.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.binding.movieItem = items[position]
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding: ItemChannelMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_channel_movie,
                parent, false)
        binding.callback = callback
        return MovieHolder(binding)
    }

    public class MovieHolder(val binding: ItemChannelMovieBinding) : RecyclerView.ViewHolder(binding.root)

}