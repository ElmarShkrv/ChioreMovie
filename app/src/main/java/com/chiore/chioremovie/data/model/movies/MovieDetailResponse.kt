package com.chiore.chioremovie.data.model.movies

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(
    tableName = "movies"
)
data class MovieDetailResponse(
    val adult: Boolean,
    val backdrop_path: String,
    @Ignore
    val belongs_to_collection: Any,
    val budget: Int,
    @Ignore
    val genres: List<Genre>,
    val homepage: String,
    @PrimaryKey
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    @Ignore
    val production_companies: List<ProductionCompany>,
    @Ignore
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    @Ignore
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
) {
    constructor(
        adult: Boolean,
        backdrop_path: String,
        budget: Int,
        homepage: String,
        id: Int,
        imdb_id: String,
        original_language: String,
        original_title: String,
        overview: String,
        popularity: Double,
        poster_path: String,
        release_date: String,
        revenue: Int,
        runtime: Int,
        status: String,
        tagline: String,
        title: String,
        video: Boolean,
        vote_average: Double,
        vote_count: Int,
    ) : this(
        adult,
        backdrop_path,
        Any(),
        budget,
        emptyList(),
        homepage,
        id,
        imdb_id,
        original_language,
        original_title,
        overview,
        popularity,
        poster_path,
        emptyList(),
        emptyList(),
        release_date,
        revenue,
        runtime,
        emptyList(),
        status,
        tagline,
        title,
        video,
        vote_average,
        vote_count
    )
}

data class Genre(
    val id: Int,
    val name: String,
)

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String,
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String,
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String,
)
