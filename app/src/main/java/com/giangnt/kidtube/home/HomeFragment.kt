package com.giangnt.kidtube.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giangnt.kidtube.R
import com.giangnt.kidtube.base.fragment.LoadDataFragment
import com.giangnt.kidtube.databinding.FragmentHomeBinding
import com.giangnt.kidtube.model.User
import com.giangnt.kidtube.repo.Repo

class HomeFragment : LoadDataFragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = HomeViewModel.Factory(
                activity!!.application, Repo())

        val model = ViewModelProviders.of(this, factory)
                .get(HomeViewModel::class.java)

        binding.homeViewModel = model
        subscribeUi(model)
    }

    private fun subscribeUi(viewModel: HomeViewModel) {
        viewModel.getObservableUser().observe(this, Observer<User> { user -> viewModel.setUser(user) })
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