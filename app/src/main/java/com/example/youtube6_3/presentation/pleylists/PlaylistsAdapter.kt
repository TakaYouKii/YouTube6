package com.example.youtube6_3.presentation.pleylists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtube6_3.data.model.PlaylistsModel
import com.example.youtube6_3.databinding.PlaylistItemsBinding

class PlaylistsAdapter(private val onClick:(playlistItem:PlaylistsModel.Item)->Unit): RecyclerView.Adapter<PlaylistsAdapter.PlaylistViewHolder>(){

    private var list = mutableListOf<PlaylistsModel.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addPlaylist(playlist: List<PlaylistsModel.Item>){
        list.addAll(playlist)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(PlaylistItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    override fun getItemCount(): Int = list.size




  inner  class PlaylistViewHolder(private val binding: PlaylistItemsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(playerListModel:PlaylistsModel.Item){
            with(binding){
                tvTitle.text=playerListModel.snippet.title
                tvCount.text=playerListModel.contentDetails.itemCount.toString()+" video series"
                Glide.with(image).load(playerListModel.snippet.thumbnails.default.url).into(image)
                itemView.setOnClickListener {
                    onClick(playerListModel)
                }
            }
        }
    }

}