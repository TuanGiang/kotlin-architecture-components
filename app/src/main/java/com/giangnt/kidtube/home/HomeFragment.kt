package com.giangnt.kidtube.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
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
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.model.User
import com.giangnt.kidtube.repo.Repo
import com.giangnt.kidtube.support.EndlessRecyclerViewScrollListener


class HomeFragment : LoadDataFragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var homeAdapter: MovieAdapter
    lateinit var endlessScroll: EndlessRecyclerViewScrollListener
    lateinit var model: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeAdapter = MovieAdapter()
        binding.rcMovie.adapter = homeAdapter
        val mDividerItemDecoration = DividerItemDecoration(binding.rcMovie.context,
                DividerItemDecoration.VERTICAL)
        binding.rcMovie.addItemDecoration(mDividerItemDecoration)

        endlessScroll = object : EndlessRecyclerViewScrollListener(binding.rcMovie.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                model.getMoreHome()
            }
        }
        binding.rcMovie.clearOnScrollListeners()
        binding.rcMovie.addOnScrollListener(endlessScroll)

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


        viewModel.getObservableMovies().observe(this, Observer<ArrayList<MovieItem>> { items ->
            items.let { homeAdapter.setList(items!!) }
            binding.executePendingBindings()
        })
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