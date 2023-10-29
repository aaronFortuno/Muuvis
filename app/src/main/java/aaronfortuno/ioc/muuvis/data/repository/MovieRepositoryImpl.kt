package aaronfortuno.ioc.muuvis.data.repository

import aaronfortuno.ioc.muuvis.data.dao.MovieDao
import aaronfortuno.ioc.muuvis.data.entity.MovieEntity
import androidx.lifecycle.LiveData

class MovieRepositoryImpl(
    private val movieDao: MovieDao
) : MovieRepository {
    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }

    override suspend fun insertNewMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: MovieEntity) {
        movieDao.deleteMovie(movie)
    }
}