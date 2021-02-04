package com.shaadi.demo.ui.profilelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.shaadi.demo.R
import com.shaadi.demo.constant.RetrofitStatus
import com.shaadi.demo.databinding.FragmentProfileListBinding
import com.shaadi.demo.model.Profile
import com.shaadi.demo.ui.profilelist.adapter.ProfileEventListener
import com.shaadi.demo.ui.profilelist.adapter.ProfileListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileListFragment : Fragment(), ProfileEventListener {
    private lateinit var binding: FragmentProfileListBinding
    private val profileViewModel: ProfileViewModel by viewModels()

    @Inject
    lateinit var profileListAdapter: ProfileListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile_list, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        profileViewModel.profiles.observe(viewLifecycleOwner, Observer { response ->
            when (response.status) {
                RetrofitStatus.SUCCESS -> response.results.let { profiles ->
                    profileListAdapter.setProfiles(profiles, this)
                    binding.textError.visibility = View.GONE
                    binding.recyclerview.visibility = View.VISIBLE
                }
                else -> {
                    binding.textError.text = resources.getString(R.string.text_error)
                    binding.recyclerview.visibility = View.GONE
                    binding.textError.visibility = View.VISIBLE
                }
            }
            binding.swipeRefreshLayout.isRefreshing = false
        })
        binding.recyclerview.apply {
            adapter = profileListAdapter
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            profileViewModel.getProfiles(true)
        }
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun onAcceptClick(profile: Profile, position: Int) {
        profileViewModel.acceptInvite(requireContext(), profile)
        profileListAdapter.notifyItemChanged(position)
    }

    override fun onDeclineClick(profile: Profile, position: Int) {
        profileViewModel.declineInvite(requireContext(), profile)
        profileListAdapter.notifyItemChanged(position)
    }
}