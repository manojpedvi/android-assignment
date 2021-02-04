package com.shaadi.demo.ui.profilelist

import androidx.fragment.app.Fragment
import com.shaadi.demo.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileListActivity : BaseActivity() {
    override fun getFragment(): Fragment {
        return ProfileListFragment.newInstance()
    }
}