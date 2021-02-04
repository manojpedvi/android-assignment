package com.shaadi.demo.repository

import com.shaadi.demo.dao.local.LocalProfileDao
import com.shaadi.demo.dao.remote.RemoteProfileDao
import com.shaadi.demo.model.Profile
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject

@Module
@InstallIn(ActivityRetainedComponent::class)
class ProfileRepository @Inject constructor(
    private val remoteProfileDao: RemoteProfileDao,
    private val localProfileDao: LocalProfileDao
) {

    suspend fun getProfiles(refresh: Boolean = false): List<Profile> {
        if (!refresh) {
            val profiles = localProfileDao.getProfiles()
            if (profiles.isNotEmpty()) {
                return profiles
            }
        }

        localProfileDao.deleteAll()
        remoteProfileDao.getProfiles().results.also {
            localProfileDao.insert(*it.map { it }.toTypedArray())
        }
        return localProfileDao.getProfiles()
    }

    suspend fun acceptInvite(profile: Profile) {
        localProfileDao.insert(profile)
    }

    suspend fun declineInvite(profile: Profile) {
        localProfileDao.insert(profile)
    }
}