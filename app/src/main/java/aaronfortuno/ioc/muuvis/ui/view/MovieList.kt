package aaronfortuno.ioc.muuvis.ui.view

import aaronfortuno.ioc.muuvis.ui.theme.MuuvisTheme
import aaronfortuno.ioc.muuvis.ui.view.cards.MovieCard
import aaronfortuno.ioc.muuvis.ui.viewmodel.MovieViewModel
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MovieList(viewModel: MovieViewModel) {
    val movies = viewModel.allMovies.observeAsState(initial = emptyList()).value
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        LazyRow(modifier = Modifier
            .fillMaxSize()
        ) {
            itemsIndexed(movies) { _, movie ->
                MovieCard(movie, viewModel)
            }
        }
    } else {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
        ) {
            itemsIndexed(movies) { _, movie ->
                MovieCard(movie, viewModel)
            }
        }
    }


}

@Preview(
    showSystemUi = true
)
@Composable
fun MovieListPreview() {
    MuuvisTheme {
        // MovieList()
    }
}