package aaronfortuno.ioc.muuvis.ui.viewmodel

import aaronfortuno.ioc.muuvis.App
import aaronfortuno.ioc.muuvis.data.entity.MovieEntity
import aaronfortuno.ioc.muuvis.data.repository.MovieRepository
import aaronfortuno.ioc.muuvis.data.repository.MovieRepositoryImpl
import androidx.lifecycle.ViewModel

class MovieViewModel(
    private val repository: MovieRepository = MovieRepositoryImpl(App.db.movieDao())
) : ViewModel() {

    fun addMovie(
        title: String,
        description: String,
        imageUrl: String
    ) {
        val movie = MovieEntity(0, title, description, imageUrl)
        repository.insertNewMovie(movie)
    }

}