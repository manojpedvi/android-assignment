package com.shaadi.demo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.shaadi.demo.R

abstract class BaseActivity : AppCompatActivity() {
    abstract fun getFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, getFragment())
                .commit()
        }
    }
}