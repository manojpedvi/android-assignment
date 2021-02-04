package com.shaadi.demo.ui.profilelist.adapter

import com.shaadi.demo.model.Profile

interface ProfileEventListener {

    fun onAcceptClick(profile: Profile, position: Int)
    fun onDeclineClick(profile: Profile, position: Int)
}