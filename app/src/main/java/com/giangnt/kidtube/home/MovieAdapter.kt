package com.giangnt.kidtube.home

import android.databinding.DataBindingUtil
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
 * Location: com.giangnt.kidtube.base - MovieAdapter
 */
class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

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
        val binding: ItemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie,
                parent, false)
        return MovieHolder(binding)
    }

    public class MovieHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

}