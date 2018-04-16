package com.giangnt.kidtube.account

import android.os.Bundle
import com.giangnt.kidtube.base.fragment.LoadDataFragment

/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * <p>
 * Copyright 2011 - 2016 ARIS-VN, Inc. All rights reserved.
 * Created by: giang.nt on 10:32 AM - 4/16/2018
 * Email: giang.nt@aris-vn.com
 * Location: com.giangnt.kidtube.account - AccountFragment
 */
class AccountFragment : LoadDataFragment() {
    companion object {
        public fun newInstance(): AccountFragment {
            val args = Bundle()
            val fragment = AccountFragment()
            fragment.arguments = args
            return fragment
        }
    }
}