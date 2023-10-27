package aaronfortuno.ioc.muuvis.ui.view

import aaronfortuno.ioc.muuvis.data.entity.MovieEntity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable

@Composable
fun MovieList(movies: List<MovieEntity>) {
    LazyColumn {
        itemsIndexed(movies) { index, movie ->
            MovieCard(movie = movie)
        }
    }
}