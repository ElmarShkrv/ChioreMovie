package com.chiore.chioremovie.data.model.movies

data class MovieResponse(
    val dates: Dates,
    val page: Int,
    val results: List<ResultDto>,
    val total_pages: Int,
    val total_results: Int
)