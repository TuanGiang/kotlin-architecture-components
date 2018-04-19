package com.giangnt.kidtube.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.giangnt.kidtube.R
import com.giangnt.kidtube.databinding.ActivitySearchChannelBinding
import com.giangnt.kidtube.model.Channel
import com.giangnt.kidtube.repo.Repo
import kotlinx.android.synthetic.main.activity_search_channel.*

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 9:38 AM - 4/19/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.search - SearchChannelActivity
 */
public class SearchChannelActivity : AppCompatActivity(), ChannelSearchClickCallBack {

    lateinit var binding: ActivitySearchChannelBinding
    lateinit var model: SearchChannelViewModel
    lateinit var searchChannelAdapter: SearchChannelAdapter
    lateinit var selectedChannelAdapter: SelectedChannelAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_channel)
        searchChannelAdapter = SearchChannelAdapter(this)
        selectedChannelAdapter = SelectedChannelAdapter(this)

        binding.rcChannel.adapter = searchChannelAdapter
        binding.rcChannelSelected.adapter = selectedChannelAdapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rcChannelSelected.layoutManager = layoutManager


        val factory = SearchChannelViewModel.Factory(
                application, Repo())
        model = ViewModelProviders.of(this, factory)
                .get(SearchChannelViewModel::class.java)

        binding.searchModel = model
        subscribeUi(model)

        binding.btnSearch.setOnClickListener { search(edtSearch.text.toString()) }

        binding.btnSave.setOnClickListener { save() }
    }

    private fun save() {
        model.save()
    }

    private fun subscribeUi(model: SearchChannelViewModel) {
        model.getObservableResult().observe(this, Observer<ArrayList<Channel>> { items ->
            searchChannelAdapter.setList(items)
        })

        model.getObservableSelected().observe(this, Observer<ArrayList<Channel>> { items ->
            selectedChannelAdapter.setList(items)
        })

    }

    fun search(query: String) {
        if (TextUtils.isEmpty(query)) {
            return
        }
        model.searchChannel(query)
    }

    override fun onSelect(channel: Channel) {
        model.addSelected(channel)
    }

    override fun onRemove(channel: Channel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SearchChannelActivity::class.java)
        }
    }

}