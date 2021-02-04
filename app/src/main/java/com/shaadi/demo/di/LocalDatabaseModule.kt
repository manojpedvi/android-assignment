package com.shaadi.demo.di

import android.content.Context
import com.shaadi.demo.dao.local.LocalProfileDao
import com.shaadi.demo.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class LocalDatabaseModule {
    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): LocalProfileDao {
        return appDatabase.localProfileDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getDatabase(
            appContext
        )
    }
}