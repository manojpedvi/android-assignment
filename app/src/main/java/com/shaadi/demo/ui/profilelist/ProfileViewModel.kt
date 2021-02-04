package com.shaadi.demo.ui.profilelist

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaadi.demo.R
import com.shaadi.demo.constant.RetrofitStatus
import com.shaadi.demo.model.Profile
import com.shaadi.demo.model.ProfileResponse
import com.shaadi.demo.repository.ProfileRepository
import com.shaadi.demo.utils.Retrofit.printRetrofitError
import kotlinx.coroutines.launch

class ProfileViewModel @ViewModelInject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _profiles = MutableLiveData<ProfileResponse>()
    val profiles: LiveData<ProfileResponse> = _profiles

    init {
        getProfiles()
    }

    fun getProfiles(refresh: Boolean = false) = viewModelScope.launch {
        try {
            _profiles.postValue(
                ProfileResponse(
                    RetrofitStatus.SUCCESS,
                    profileRepository.getProfiles(refresh)
                )
            )
        } catch (e: Exception) {
            _profiles.postValue(
                ProfileResponse(
                    RetrofitStatus.FAILURE
                )
            )
            e.printRetrofitError()
        }
    }

    fun acceptInvite(context: Context, profile: Profile) = viewModelScope.launch {
        profile.inviteAccepted = true
        profile.status = context.getString(R.string.text_invite_accepted)
        profileRepository.acceptInvite(profile)
    }

    fun declineInvite(context: Context, profile: Profile) = viewModelScope.launch {
        profile.inviteDeclined = true
        profile.status = context.getString(R.string.text_invite_declined)
        profileRepository.declineInvite(profile)
    }
}