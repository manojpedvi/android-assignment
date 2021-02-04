package com.shaadi.demo.ui.profilelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shaadi.demo.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, ProfileListFragment())
                .commit()
        }
    }
}