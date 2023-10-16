package com.example.youtube6_3.presentation.pleylists

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.youtube6_3.R
import com.example.youtube6_3.core.network.RemoteDataSource
import com.example.youtube6_3.core.network.RetrofitClient
import com.example.youtube6_3.data.model.PlaylistsModel
import com.example.youtube6_3.databinding.FragmentPlaylistsBinding
import com.example.youtube6_3.domain.repositoty.Repository
import com.example.youtube6_3.utils.InternetConnection


class PlaylistsFragment : Fragment() {
    private lateinit var binding: FragmentPlaylistsBinding
    private val retrofitClient = RetrofitClient().createApiService()
    private val remoteDataSource = RemoteDataSource(retrofitClient)
    private val repository=Repository(remoteDataSource)
    private val playerListViewModel = PlaylistsViewModel(repository)
    private val adapter = PlaylistsAdapter(this::onClickItem)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPlaylistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveData()
        initListeners()
        initView()
    }

    private fun initView() {
        playerListViewModel.getPlaylists()
    }

    private fun initListeners() {
        InternetConnection(requireContext()).observe(viewLifecycleOwner){isConected->
            if (!isConected){
                binding.noInternet.root.visibility=View.VISIBLE
            }
            binding.noInternet.btnTryAgain.setOnClickListener {
                if (isConected){
                    binding.noInternet.root.visibility=View.GONE
                    initLiveData()
                }
            }
        }
    }
    private fun initLiveData() {
        playerListViewModel.playlists.observe(viewLifecycleOwner){
            adapter.addPlaylist(it.items)
            binding.rvPlaylists.adapter = adapter
        }
        playerListViewModel.loading.observe(viewLifecycleOwner){loading->
            if (loading){
                binding.loading.visibility=View.VISIBLE
            }
            else{
                binding.loading.visibility=View.GONE
            }
        }
        playerListViewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
        }
    }
    private fun onClickItem(playlistsModel: PlaylistsModel.Item){
        setFragmentResult("key1", bundleOf("key" to playlistsModel))
        findNavController().navigate(R.id.playlistItemsFragment)
        Log.e("ololo", "onClickItem: $playlistsModel", )
    }
}