package com.example.youtube6_3.presentation.playlist_items

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.youtube6_3.core.network.RemoteDataSource
import com.example.youtube6_3.core.network.RetrofitClient
import com.example.youtube6_3.data.model.PlaylistsModel
import com.example.youtube6_3.databinding.FragmentPlaylistItemsBinding
import com.example.youtube6_3.domain.repositoty.Repository
import com.example.youtube6_3.utils.InternetConnection

class PlaylistItemsFragment : Fragment() {

    private lateinit var binding: FragmentPlaylistItemsBinding
    private val retrofitClient = RetrofitClient().createApiService()
    private val remoteDataSource = RemoteDataSource(retrofitClient)
    private val repository = Repository(remoteDataSource)
    private val playerListItemViewModel = PlayListItemViewModel(repository)

    private var adapter = PlaylistItemsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPlaylistItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initGetResultListener()
        initLiveData()
    }

    private fun initListeners() {
        InternetConnection(requireContext()).observe(viewLifecycleOwner) { isConect ->
            if (!isConect) {
                binding.noInternet.root.visibility = View.VISIBLE
            }
            binding.noInternet.btnTryAgain.setOnClickListener {
                if (isConect) {
                    binding.noInternet.root.visibility = View.GONE
                    initLiveData()
                }
            }
        }
    }

    private fun initLiveData() {
        playerListItemViewModel.playlistsItem.observe(viewLifecycleOwner) {
            getData(it.items)
        }
        playerListItemViewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
            }
        }
        playerListItemViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getData(playListItem: List<PlaylistsModel.Item>) {
        adapter.addData(playListItem)
        binding.rvVideo.adapter = adapter
    }

    private fun initGetResultListener() {
        setFragmentResultListener("key1") { _, bundle ->
            bundle.getSerializable("key")
                ?.let { item ->
                    val _item = item as PlaylistsModel.Item
                    CordinatorLayout(_item)
                    initView(_item.id)
                    Log.e("ololo", "initGetResultListener: $_item")
                }
        }

    }

    private fun initView(id: String) {
        playerListItemViewModel.getPlaylists(id)
    }

    @SuppressLint("SetTextI18n")
    private fun CordinatorLayout(playListItem: PlaylistsModel.Item) {
        with(binding) {
            tvTitle.text = playListItem.snippet.title
            tvDesc.text = playListItem.snippet.description
            tvCountVideo.text = playListItem.contentDetails.itemCount.toString() + "video series"
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }

        }
    }
}