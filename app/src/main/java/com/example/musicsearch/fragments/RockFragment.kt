package com.example.musicsearch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.musicsearch.ApiService
import com.example.musicsearch.MusicListAdapter
import com.example.musicsearch.MusicResponse
import com.example.musicsearch.databinding.FragmentRockBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RockFragment: Fragment() {
    private var _binding: FragmentRockBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRockBinding.inflate(inflater, container, false)

        fetchSongList("rock")
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
                        val userAdapter = MusicListAdapter(response.body()!!.results)
                        binding.rvRock.adapter = userAdapter
                    }
                }

                override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }

            })

    }

}