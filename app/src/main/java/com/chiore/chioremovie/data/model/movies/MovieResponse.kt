package com.chiore.chioremovie.data.model.movies

import com.chiore.chioremovie.domain.model.Result

data class MovieResponse(
    val dates: Dates,
    val page: Int,
    val results: List<ResultDto>,
    val total_pages: Int,
    val total_results: Int
)

data class Dates(
    val maximum: String,
    val minimum: String
)

data class ResultDto(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val key: String,
)

fun ResultDto.toResult(): Result {
    return Result(
        backdrop_path = backdrop_path,
        genre_ids = genre_ids,
        original_title = original_title,
        poster_path = poster_path,
        vote_average = vote_average,
        release_date = release_date,
        key = key
    )
}