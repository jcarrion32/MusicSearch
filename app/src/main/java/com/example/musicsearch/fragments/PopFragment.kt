package com.example.musicsearch.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.musicsearch.ApiService
import com.example.musicsearch.MusicListAdapter
import com.example.musicsearch.MusicResponse
import com.example.musicsearch.MusicResults
import com.example.musicsearch.databinding.FragmentPopBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopFragment: Fragment(){
    private var _binding: FragmentPopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopBinding.inflate(inflater, container, false)

        fetchSongList("pop")
        return binding.root
    }

    private fun fetchSongList(songType: String) {
        ApiService.getRetrofitInstance().create(ApiService::class.java)
            .getMusicList(songType)
            .enqueue(object: Callback<MusicResponse> {
                override fun onResponse(
                    call: Call<MusicResponse>,
                    response: Response<MusicResponse>
                ) {
                    if (response.isSuccessful){
                        val userAdapter = MusicListAdapter(response.body()!!.results, ::playSelectedSong)
                        binding.rvPop.adapter = userAdapter
                    }
                }

                override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }

            })

    }

    fun playSelectedSong (selectedSong: MusicResults){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse(selectedSong.previewUrl), "audio/mp4")
        startActivity(intent)
    }
}