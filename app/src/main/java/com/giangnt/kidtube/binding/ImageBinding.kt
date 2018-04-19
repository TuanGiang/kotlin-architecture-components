package com.giangnt.kidtube.binding

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.giangnt.kidtube.R

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 1:42 PM - 4/17/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.binding - ImageBinding
 */


@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    when (this.id) {
        R.id.imgMovie -> {
            Glide.with(context).load(url).into(this)
        }
        R.id.imgChannel -> {
            Glide.with(context).load(url).into(this)
        }
        else -> {
            Glide.with(context).load(url).into(this)
        }
    }

}

@BindingAdapter("visibleGone")
fun View.setGone(isGone: Boolean) {
    if (isGone) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }

}


