package com.shaadi.demo.model

import android.os.Parcelable
import com.shaadi.demo.constant.RetrofitStatus
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileResponse(
    val status: RetrofitStatus = RetrofitStatus.UNKNOWN,
    val results: List<Profile> = emptyList()
) : Parcelable