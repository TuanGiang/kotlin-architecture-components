package com.giangnt.kidtube.owner

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
import com.afollestad.materialdialogs.MaterialDialog
import com.giangnt.kidtube.R
import com.giangnt.kidtube.action.ActionType
import com.giangnt.kidtube.base.fragment.DataFragment
import com.giangnt.kidtube.databinding.FragmentMyVideoBinding
import com.giangnt.kidtube.home.HomeAdapter
import com.giangnt.kidtube.home.HomeClickCallback
import com.giangnt.kidtube.model.Channel
import com.giangnt.kidtube.model.MovieItem
import com.giangnt.kidtube.model.User
import com.giangnt.kidtube.movie.MoviePlayActivity
import com.giangnt.kidtube.nav.MovieNav
import com.giangnt.kidtube.repo.Repo

class MyVideoFragment : DataFragment(), HomeClickCallback {


    lateinit var binding: FragmentMyVideoBinding
    lateinit var homeAdapter: HomeAdapter
    lateinit var model: MyVideoViewModel

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_video, container, false)

        homeAdapter = HomeAdapter(this, this)
        binding.rcMovie.adapter = homeAdapter
        val mDividerItemDecoration = DividerItemDecoration(binding.rcMovie.context,
                DividerItemDecoration.VERTICAL)
        binding.rcMovie.addItemDecoration(mDividerItemDecoration)

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = MyVideoViewModel.Factory(
                activity!!.application, Repo())

        model = ViewModelProviders.of(this, factory)
                .get(MyVideoViewModel::class.java)

        binding.viewModel = model
        subscribeUi(model)
    }

    private fun subscribeUi(viewModel: MyVideoViewModel) {
        viewModel.getObservableUser().observe(this, Observer<User> { user -> viewModel.setUser(user) })


        viewModel.getObservableMovies().observe(this, Observer(homeAdapter::submitList))
    }


    override fun onClick(movieItem: MovieItem) {
        nav?.onGoPlayVideo(movieItem, MoviePlayActivity.FROM_MY_VIDEO)
    }

    override fun onClickChannel(channel: Channel) {
        nav?.onGoChannelDetail(channel)
    }

    override fun onClickAction(id: String, type: ActionType) {
        MaterialDialog.Builder(context!!)
                .items(R.array.removes)
                .itemsCallback({ dialog, view, which, text ->
                    when (which) {
                        0 -> performAction(id, type)
                    }
                })
                .show()
    }

    companion object {
        public fun newInstance(): MyVideoFragment {
            val args = Bundle()
            val fragment = MyVideoFragment()
            fragment.arguments = args
            return fragment
        }
    }
}