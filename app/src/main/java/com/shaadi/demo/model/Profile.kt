package com.shaadi.demo.model

import android.os.Parcelable
import android.webkit.WebSettings
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.shaadi.demo.R
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "profile_table")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val thumbnailUrl: String,
    val name: String,
    val age: Int,
    val city: String,
    val state: String,
    val country: String,
    var inviteAccepted: Boolean = false,
    var inviteDeclined: Boolean = false,
    var status: String = ""
) : Parcelable {

    companion object {

        @JvmStatic
        @BindingAdapter("image")
        fun loadImage(imageView: ImageView, url: String) {
            Glide.with(imageView.context)
                .load(GlideUrl(url, LazyHeaders.Builder().addHeader("User-Agent", WebSettings.getDefaultUserAgent(imageView.context)).build()))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()
                .placeholder(R.color.colorShimmer)
                .error(R.color.colorShimmer)
                .into(imageView)
        }
    }
}