package com.giangnt.kidtube.channel

import android.os.Bundle
import com.giangnt.kidtube.base.fragment.LoadDataFragment

class ChannelListFragment : LoadDataFragment() {
    companion object {
        public fun newInstance(): ChannelListFragment {
            val args = Bundle()
            val fragment = ChannelListFragment()
            fragment.arguments = args
            return fragment
        }
    }

}