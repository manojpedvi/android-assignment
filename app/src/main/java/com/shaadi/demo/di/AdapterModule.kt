package com.shaadi.demo.di

import com.shaadi.demo.ui.profilelist.adapter.ProfileListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class AdapterModule {

    @Provides
    @ActivityScoped
    fun provideProfileListAdapter(): ProfileListAdapter = ProfileListAdapter()
}