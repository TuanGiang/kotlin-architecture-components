package com.giangnt.kidtube

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.giangnt.kidtube.account.PersonalFragment
import com.giangnt.kidtube.channel.ChannelListFragment
import com.giangnt.kidtube.channel.detail.ChannelFragment
import com.giangnt.kidtube.home.HomeFragment
import com.giangnt.kidtube.model.Channel
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.movie.MoviePlayActivity
import com.giangnt.kidtube.nav.ChannelNav
import com.giangnt.kidtube.nav.MovieNav
import com.giangnt.kidtube.owner.MyVideoFragment
import com.ncapdevi.fragnav.FragNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FragNavController.TransactionListener, FragNavController.RootFragmentListener, ChannelNav, MovieNav {

    private val TAB_HOME = 0
    private val TAB_OWNER = 1
    private val TAB_CHANNEL = 2
    private val TAB_ACCOUNT = 3
    private val TAB_COUNT = 4
    lateinit var fragNavController: FragNavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomBar.enableAnimation(false)
        bottomBar.enableItemShiftingMode(false)
        bottomBar.enableShiftingMode(false)
        if (savedInstanceState == null) {
            fragNavController = FragNavController.newBuilder(savedInstanceState, supportFragmentManager, R.id.mainContainer)
                    .transactionListener(this)
                    .rootFragmentListener(this, TAB_COUNT)
                    .switchController { i, _ ->
                        when (i) {
                            TAB_HOME -> bottomBar.selectedItemId = R.id.action_home
                            TAB_OWNER -> bottomBar.selectedItemId = R.id.action_owner
                            TAB_CHANNEL -> bottomBar.selectedItemId = R.id.action_account
                            TAB_ACCOUNT -> bottomBar.selectedItemId = R.id.action_account
                            else -> {
                                throw Exception("Invalid tab")
                            }
                        }
                    }
                    .build()
        }

        bottomBar.setOnNavigationItemReselectedListener({ fragNavController.clearStack() })

        bottomBar.setOnNavigationItemSelectedListener({ item ->
            when (item.itemId) {
                R.id.action_home -> {
                    fragNavController.switchTab(TAB_HOME)
                }
                R.id.action_owner -> {
                    fragNavController.switchTab(TAB_OWNER)
                }
                R.id.action_channel -> {
                    fragNavController.switchTab(TAB_CHANNEL)
                }
                R.id.action_account -> {
                    fragNavController.switchTab(TAB_ACCOUNT)
                }
            }
            true
        })
    }

    override fun onGoChannelDetail(channel: Channel) {
        fragNavController.pushFragment(ChannelFragment.newInstance(channel))

    }

    override fun onGoPlayVideo(movieItem: MovieItem) {
        startActivity(MoviePlayActivity.getIntent(this, movieItem))
    }

    override fun getRootFragment(p0: Int): Fragment {
        return when (p0) {
            TAB_HOME -> HomeFragment.newInstance()
            TAB_OWNER -> MyVideoFragment.newInstance()
            TAB_CHANNEL -> ChannelListFragment.newInstance()
            TAB_ACCOUNT -> PersonalFragment.newInstance()
            else -> throw Exception("Invalid tab")
        }

    }

    override fun onFragmentTransaction(p0: Fragment?, p1: FragNavController.TransactionType?) {
    }

    override fun onTabTransaction(p0: Fragment?, p1: Int) {
    }
}
