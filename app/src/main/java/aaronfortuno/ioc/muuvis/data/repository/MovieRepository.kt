package aaronfortuno.ioc.muuvis.data.repository

import aaronfortuno.ioc.muuvis.data.entity.MovieEntity
import androidx.lifecycle.LiveData

interface MovieRepository {
    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun insertNewMovie(movie: MovieEntity)
}
