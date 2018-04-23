package com.giangnt.kidtube.channel

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giangnt.kidtube.R
import com.giangnt.kidtube.base.fragment.DataFragment
import com.giangnt.kidtube.databinding.FragmentChannelListBinding
import com.giangnt.kidtube.model.Channel
import com.giangnt.kidtube.nav.ChannelNav
import com.giangnt.kidtube.repo.Repo


class ChannelListFragment : DataFragment(), ChannelClickCallback {

    lateinit var binding: FragmentChannelListBinding
    lateinit var channelAdapter: ChannelAdapter
    lateinit var model: ChannelListViewModel

    var nav: ChannelNav? = null


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is ChannelNav) {
            nav = context
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if (activity is ChannelNav) {
            nav = activity
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_channel_list, container, false)

        channelAdapter = ChannelAdapter(this)
        binding.rcChannel.adapter = channelAdapter
        val mDividerItemDecoration = DividerItemDecoration(binding.rcChannel.context,
                DividerItemDecoration.VERTICAL)
        binding.rcChannel.addItemDecoration(mDividerItemDecoration)

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = ChannelListViewModel.Factory(
                activity!!.application, Repo())

        model = ViewModelProviders.of(this, factory)
                .get(ChannelListViewModel::class.java)

        subscribeUi(model)
    }

    private fun subscribeUi(viewModel: ChannelListViewModel) {

        viewModel.getObservableChannels().observe(this, Observer<List<Channel>> { items ->
            items.let { channelAdapter.setList(items!!) }
            binding.executePendingBindings()
        })
    }

    override fun onClick(channel: Channel) {
        nav?.onGoChannelDetail(channel)
    }

    companion object {
        public fun newInstance(): ChannelListFragment {
            val args = Bundle()
            val fragment = ChannelListFragment()
            fragment.arguments = args
            return fragment
        }
    }

}