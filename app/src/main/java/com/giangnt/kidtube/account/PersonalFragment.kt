package com.giangnt.kidtube.account

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giangnt.kidtube.R
import com.giangnt.kidtube.base.fragment.LoadDataFragment
import com.giangnt.kidtube.databinding.FragmentLoginBinding
import com.giangnt.kidtube.model.User
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 10:32 AM - 4/16/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.account - PersonalFragment
 */
class PersonalFragment : LoadDataFragment() {

    lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(PersonalViewModel::class.java)
        binding.personalViewModel = viewModel
        subscribeUi(viewModel)
    }

    private fun subscribeUi(viewModel: PersonalViewModel) {
        viewModel.getObservableUser().observe(this, Observer<User> { user -> viewModel.setUser(user) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin.setOnClickListener { _ ->
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                val user = User(email, email)
                AccountManager.setUser(user)
            }
        }
    }

    companion object {
        public fun newInstance(): PersonalFragment {
            val args = Bundle()
            val fragment = PersonalFragment()
            fragment.arguments = args
            return fragment
        }
    }
}