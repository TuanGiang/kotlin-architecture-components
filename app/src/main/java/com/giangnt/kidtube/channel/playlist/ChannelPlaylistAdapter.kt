package com.giangnt.kidtube.channel.playlist

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.giangnt.kidtube.R
import com.giangnt.kidtube.action.ClickActionCallBack
import com.giangnt.kidtube.channel.movies.ChannelMovieAdapter
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
class ChannelPlaylistAdapter(val callback: ChannelPlaylistCallback,val actionCallBack: ClickActionCallBack) : PagedListAdapter<Playlist, ChannelPlaylistAdapter.PlaylistHolder>(ChannelPlaylistAdapter.diffCallback) {

    override fun onBindViewHolder(holder: PlaylistHolder, position: Int) {
        holder.binding.playlist = getItem(position)
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistHolder {
        val binding: ItemPlaylistBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_playlist,
                parent, false)
        binding.callback = callback
        binding.actionCallBack = actionCallBack
        return PlaylistHolder(binding)
    }

    public class PlaylistHolder(val binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root)

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
        private val diffCallback = object : DiffUtil.ItemCallback<Playlist>() {
            override fun areItemsTheSame(oldItem: Playlist, newItem: Playlist): Boolean =
                    oldItem.id == newItem.id

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: Playlist, newItem: Playlist): Boolean =
                    oldItem == newItem
        }
    }
}