package com.example.youtube6_3.presentation.playlist_items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.youtube6_3.data.model.PlaylistsModel
import com.example.youtube6_3.databinding.VideoItemsBinding

class PlaylistItemsAdapter : Adapter<PlaylistItemsAdapter.PlaylistItemsViewHolder>(){


    private val _list = mutableListOf<PlaylistsModel.Item>()
    private val list: List<PlaylistsModel.Item> get() = _list

    fun addData(playlistModelItem: List<PlaylistsModel.Item>) {
        _list.clear()
        _list.addAll(playlistModelItem)
        notifyItemRangeInserted(_list.size, playlistModelItem.size - _list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemsViewHolder {
        return PlaylistItemsViewHolder(VideoItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: PlaylistItemsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class PlaylistItemsViewHolder(private val binding: VideoItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(playListItem:PlaylistsModel.Item){
            binding.tvName.text=playListItem.snippet.title
            Glide.with(binding.imgVideo).load(playListItem.snippet.thumbnails.default.url).into(binding.imgVideo)

        }

    }
}