package com.example.musicsearch.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.musicsearch.*
import com.example.musicsearch.databinding.FragmentClassicBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClassicFragment: Fragment() {
    private var _binding: FragmentClassicBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClassicBinding.inflate(inflater, container, false)

        fetchSongList("classik")
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
                        binding.rvClassic.adapter = userAdapter
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

//    fun openDetailFragment(selectedSong: MusicResults){
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.vp_fragments, SongDetailFragment.createInstance(selectedSong))
//            .addToBackStack(null)
//            .commit()
//    }
}