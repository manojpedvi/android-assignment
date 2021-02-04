package com.shaadi.demo.ui.profilelist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shaadi.demo.R
import com.shaadi.demo.databinding.ItemProfileBinding
import com.shaadi.demo.model.Profile

class ProfileListAdapter : RecyclerView.Adapter<ProfileListAdapter.ViewHolder>() {

    private val profiles = mutableListOf<Profile>()
    private var profileEventListener: ProfileEventListener? = null

    fun setProfiles(profiles: List<Profile>, profileEventListener: ProfileEventListener) {
        this.profileEventListener = profileEventListener
        this.profiles.clear()
        this.profiles.addAll(profiles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_profile,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = profiles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(profiles[position])

    inner class ViewHolder(
        @NonNull val profileBinding: ItemProfileBinding
    ) : RecyclerView.ViewHolder(profileBinding.root) {

        fun bind(profile: Profile) {
            profileBinding.apply {
                this.profile = profile
                this.btnAccept.setOnClickListener {
                    profileEventListener?.onAcceptClick(profile, adapterPosition)
                }

                this.btnDecline.setOnClickListener {
                    profileEventListener?.onDeclineClick(profile, adapterPosition)
                }

                if (profile.status.isNotEmpty()) {
                    this.groupButtons.visibility = View.GONE
                    this.status.visibility = View.VISIBLE
                } else {
                    this.groupButtons.visibility = View.VISIBLE
                    this.status.visibility = View.GONE
                }
            }
        }
    }
}