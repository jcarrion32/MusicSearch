package com.example.musicsearch

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50

    @GET("search")
    fun getMusicList(
        @Query("term") term: String,
        @Query("amp;media") media: String = "music",
        @Query("amp;entity") entity: String = "song",
        @Query("amp;limit") limit: Int = 50
    ): Call<MusicResponse>

    companion object{
        private var retrofit: Retrofit? = null

        fun getRetrofitInstance(): Retrofit{
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl("https://itunes.apple.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}