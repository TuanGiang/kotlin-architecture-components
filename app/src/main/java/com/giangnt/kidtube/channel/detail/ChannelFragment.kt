package com.giangnt.kidtube.channel.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giangnt.kidtube.R
import com.giangnt.kidtube.base.fragment.LoadDataFragment
import com.giangnt.kidtube.model.Channel
import kotlinx.android.synthetic.main.fragment_channel.*

class ChannelFragment : LoadDataFragment() {
    lateinit var channelPagerAdapter: ChannelPagerAdapter
    lateinit var channel: Channel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        channel = arguments!!.get(CHANNEL) as Channel
        channelPagerAdapter = ChannelPagerAdapter(childFragmentManager, channel)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_channel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pager.adapter = channelPagerAdapter
        tab_layout.setupWithViewPager(pager)
    }

    companion object {
        val CHANNEL = "CHANNEL"
        fun newInstance(channel: Channel): ChannelFragment {
            val args = Bundle()
            args.putSerializable(CHANNEL, channel)
            val fragment = ChannelFragment()
            fragment.arguments = args
            return fragment
        }
    }

}