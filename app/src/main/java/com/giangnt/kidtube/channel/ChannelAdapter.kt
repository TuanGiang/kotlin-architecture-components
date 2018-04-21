package com.giangnt.kidtube.channel

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.giangnt.kidtube.R
import com.giangnt.kidtube.databinding.ItemChannelBinding
import com.giangnt.kidtube.model.Channel
import com.giangnt.kidtube.model.ChannelItem

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 1:43 PM - 4/18/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.channel - ChannelAdapter
 */
class ChannelAdapter(val callback: ChannelClickCallback) : RecyclerView.Adapter<ChannelAdapter.ChannelHolder>() {

    var items = ArrayList<Channel>()

    fun setList(list: List<Channel>) {
        items.clear()
        items.addAll(list)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChannelAdapter.ChannelHolder, position: Int) {
        holder.binding.channel = items[position]
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelHolder {
        val binding: ItemChannelBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_channel,
                parent, false)
        binding.callBack = callback
        return ChannelHolder(binding)
    }

    class ChannelHolder(val binding: ItemChannelBinding) : RecyclerView.ViewHolder(binding.root)


}