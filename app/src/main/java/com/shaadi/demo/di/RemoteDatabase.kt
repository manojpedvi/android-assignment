package com.shaadi.demo.di

import com.shaadi.demo.dao.remote.RemoteProfileDao
import com.shaadi.demo.utils.Retrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RemoteDatabase {

    @Provides
    @Singleton
    fun provideRemoteProfileDAO(): RemoteProfileDao = Retrofit.getClient().create(RemoteProfileDao::class.java)
}