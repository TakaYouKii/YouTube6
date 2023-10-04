package com.example.youtube6_3.presentation.pleylists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.youtube6_3.core.network.RetrofitClient
import com.example.youtube6_3.core.utils.Status
import com.example.youtube6_3.databinding.FragmentPlaylistsBinding
import com.example.youtube6_3.domain.repositoty.Repository


class PlaylistsFragment : Fragment() {

    private lateinit var binding: FragmentPlaylistsBinding

    private val playlistsViewModel = PlaylistsViewModel(Repository(RetrofitClient().createApiService()))
    private val adapter = PlaylistsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaylistsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveData()

    }

    private fun initLiveData() {
        playlistsViewModel.getPlaylists().observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let { adapter.addPlaylist(it.items) }
                    binding.rvPlaylists.adapter = adapter
                    playlistsViewModel.loading.value = false
                }

                Status.ERROR -> {
                    if (resource.data == null) {
                        Toast.makeText(requireContext(), "Данные не пришли", Toast.LENGTH_SHORT)
                            .show()
                    }
                    playlistsViewModel.loading.value = false
                }

                Status.LOADING -> {
                    playlistsViewModel.loading.value = true
                }
            }
        }

        playlistsViewModel.loading.observe(requireActivity()) {
            if (it) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
            }
        }
    }


}