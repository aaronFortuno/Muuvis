package aaronfortuno.ioc.muuvis.ui.viewmodel

import aaronfortuno.ioc.muuvis.App
import aaronfortuno.ioc.muuvis.data.repository.MovieRepository
import aaronfortuno.ioc.muuvis.data.repository.MovieRepositoryImpl
import androidx.lifecycle.ViewModel

class MovieViewModel(
    private val repository: MovieRepository = MovieRepositoryImpl(App.db.movieDao())
) : ViewModel() {

}