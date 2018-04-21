package com.giangnt.kidtube.home

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giangnt.kidtube.R
import com.giangnt.kidtube.base.fragment.LoadDataFragment
import com.giangnt.kidtube.databinding.FragmentHomeBinding
import com.giangnt.kidtube.model.Channel
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.model.User
import com.giangnt.kidtube.nav.MovieNav
import com.giangnt.kidtube.repo.Repo
import com.giangnt.kidtube.support.EndlessRecyclerViewScrollListener


class HomeFragment : LoadDataFragment(), HomeClickCallback {

    lateinit var binding: FragmentHomeBinding
    lateinit var homeAdapter: HomeAdapter
    lateinit var model: HomeViewModel

    var nav: MovieNav? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MovieNav) {
            nav = context
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if (activity is MovieNav) {
            nav = activity
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        homeAdapter = HomeAdapter(this)
        binding.rcMovie.adapter = homeAdapter
        val mDividerItemDecoration = DividerItemDecoration(binding.rcMovie.context,
                DividerItemDecoration.VERTICAL)
        binding.rcMovie.addItemDecoration(mDividerItemDecoration)

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = HomeViewModel.Factory(
                activity!!.application, Repo())

        model = ViewModelProviders.of(this, factory)
                .get(HomeViewModel::class.java)

        binding.homeViewModel = model
        subscribeUi(model)
    }

    private fun subscribeUi(viewModel: HomeViewModel) {
        viewModel.getObservableUser().observe(this, Observer<User> { user -> viewModel.setUser(user) })


        viewModel.getObservableMovies().observe(this,Observer(homeAdapter::submitList))
    }

    override fun onClick(movieItem: MovieItem) {
        nav?.onGoPlayVideo(movieItem)
    }

    override fun onClickChannel(channel: Channel) {
        nav?.onGoChannelDetail(channel)
    }

    companion object {
        public fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}