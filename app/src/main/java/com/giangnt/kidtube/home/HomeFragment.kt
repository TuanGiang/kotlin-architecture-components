package com.giangnt.kidtube.home

import android.os.Bundle
import com.giangnt.kidtube.base.fragment.LoadDataFragment

class HomeFragment : LoadDataFragment() {
    companion object {
        public fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}