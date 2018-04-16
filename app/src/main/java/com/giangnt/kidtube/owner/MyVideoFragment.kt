package com.giangnt.kidtube.owner

import android.os.Bundle
import com.giangnt.kidtube.base.fragment.LoadDataFragment

class MyVideoFragment : LoadDataFragment() {
    companion object {
        public fun newInstance(): MyVideoFragment {
            val args = Bundle()
            val fragment = MyVideoFragment()
            fragment.arguments = args
            return fragment
        }
    }
}