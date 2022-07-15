package com.example.musicsearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicsearch.databinding.SongListItemBinding

class MusicListAdapter(
    private val list: List<MusicResults>,
    private val openDetail: (MusicResults) -> Unit
)
    :  RecyclerView.Adapter<MusicListAdapter.MusicListViewHolder>() {

    inner class MusicListViewHolder(private val binding: SongListItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun onBind(songResult: MusicResults){
                binding.tvSongTitle.text = songResult.trackName
                binding.tvArtistName.text = songResult.artistName
                binding.tvSongPrice.text = binding.root.context.resources.getString(
                    R.string.song_price,
                    songResult.trackPrice.toString()
                )
                Glide.with(binding.ivSongPic)
                    .load(songResult.artworkUrl100)
                    .placeholder(R.drawable.ic_loading_foreground)
                    .into(binding.ivSongPic)

                binding.root.setOnClickListener{
                    openDetail(songResult)
                }

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicListViewHolder {
        return MusicListViewHolder(
            SongListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MusicListViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}