package com.example.musicsearch

data class MusicResponse(
    val results: List<MusicResults>
)

data class MusicResults(
    val artistName: String,
    val trackName: String,
    val artworkUrl100: String,
    val trackPrice: Double
)