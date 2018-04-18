package com.giangnt.kidtube.channel.playlist

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.giangnt.kidtube.R
import com.giangnt.kidtube.databinding.ItemPlaylistBinding
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.model.Playlist

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 4:33 PM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.channel.playlist - ChannelPlaylistAdapter
 */
class ChannelPlaylistAdapter(val callback: ChannelPlaylistCallback) : RecyclerView.Adapter<ChannelPlaylistAdapter.PlaylistHolder>() {

    var items = ArrayList<Playlist>()

    fun setList(list: ArrayList<Playlist>) {
        items = list
        this.notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        if (items == null) {
            return 0
        }
        return items.size
    }

    override fun onBindViewHolder(holder: PlaylistHolder, position: Int) {
        holder.binding.playlist = items[position]
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistHolder {
        val binding: ItemPlaylistBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_playlist,
                parent, false)
        binding.callback = callback
        return PlaylistHolder(binding)
    }

    public class PlaylistHolder(val binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root)
}