package com.giangnt.kidtube.search

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.giangnt.kidtube.R
import com.giangnt.kidtube.databinding.ItemChannelSearchBinding
import com.giangnt.kidtube.databinding.ItemChannelSelectedBinding
import com.giangnt.kidtube.model.Channel

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 10:00 AM - 4/19/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.search - SelectedChannelAdapter
 */
class SelectedChannelAdapter (val callback: ChannelSearchClickCallBack) : RecyclerView.Adapter<SelectedChannelAdapter.ChannelHolder>() {

    var items = ArrayList<Channel>()

    fun setList(list: ArrayList<Channel>?) {
        list.let {
            items = list!!
            this.notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChannelHolder, position: Int) {
        holder.binding.channel = items[position]
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelHolder {
        val binding: ItemChannelSelectedBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_channel_selected,
                parent, false)
        binding.callBack = callback
        return ChannelHolder(binding)
    }

    public class ChannelHolder(val binding: ItemChannelSelectedBinding) : RecyclerView.ViewHolder(binding.root)
}