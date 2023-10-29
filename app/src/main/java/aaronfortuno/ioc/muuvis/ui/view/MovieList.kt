package aaronfortuno.ioc.muuvis.ui.view

import aaronfortuno.ioc.muuvis.data.entity.MovieEntity
import aaronfortuno.ioc.muuvis.ui.theme.MuuvisTheme
import aaronfortuno.ioc.muuvis.ui.view.cards.MovieCard
import aaronfortuno.ioc.muuvis.ui.viewmodel.MovieViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val sampleMovies = listOf(
    MovieEntity(1, "Pelicula 1", "Descripción 1", "film1_davinci"),
    MovieEntity(2, "Pelicula 2", "Descripción 2", "film2_picasso"),
    MovieEntity(3, "Pelicula 3", "Descripción 3", "film3_manet")
)
@Composable
fun MovieList(viewModel: MovieViewModel) {
    val movies = viewModel.allMovies.observeAsState(initial = emptyList()).value
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        itemsIndexed(movies) { index, movie ->
            MovieCard(movie = movie)
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun MovieListPreview() {
    MuuvisTheme {
        // MovieList(movies = sampleMovies)
    }
}