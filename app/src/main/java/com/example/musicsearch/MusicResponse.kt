package com.example.musicsearch

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MusicResponse(
    val results: List<MusicResults>
)

@Parcelize
data class MusicResults(
    val artistName: String,
    val trackName: String,
    val artworkUrl100: String,
    val trackPrice: Double,
    val previewUrl: String
): Parcelable