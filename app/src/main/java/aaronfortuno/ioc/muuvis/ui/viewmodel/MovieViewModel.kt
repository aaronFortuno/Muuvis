package aaronfortuno.ioc.muuvis.ui.viewmodel

import aaronfortuno.ioc.muuvis.App
import aaronfortuno.ioc.muuvis.data.entity.MovieEntity
import aaronfortuno.ioc.muuvis.data.repository.MovieRepository
import aaronfortuno.ioc.muuvis.data.repository.MovieRepositoryImpl
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieViewModel(
    private val repository: MovieRepository = MovieRepositoryImpl(App.db.movieDao())
) : ViewModel() {
    val allMovies: LiveData<List<MovieEntity>> = repository.getAllMovies()

    suspend fun addMovie(
        title: String,
        description: String,
        imageUrl: String
    ) {
        val movie = MovieEntity(0, title, description, imageUrl)
        withContext(Dispatchers.IO) {
            repository.insertNewMovie(movie)
        }
    }
}