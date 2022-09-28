package com.chiore.chioremovie.domain.model

data class Result(
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val original_title: String,
    val poster_path: String,
    val vote_average: Double,
    val release_date: String
)
