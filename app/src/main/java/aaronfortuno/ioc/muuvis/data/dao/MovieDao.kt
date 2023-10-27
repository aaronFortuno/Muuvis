package aaronfortuno.ioc.muuvis.data.dao

import aaronfortuno.ioc.muuvis.data.entity.MovieEntity
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovie(movieId: Int): MovieEntity

    @Insert
    fun insertMovie(movie: MovieEntity)

    @Delete
    fun deleteMovie(movie: MovieEntity)
}