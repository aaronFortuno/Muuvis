package aaronfortuno.ioc.muuvis.ui.view

import aaronfortuno.ioc.muuvis.data.entity.MovieEntity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun MovieCard(
    movie: MovieEntity = MovieEntity(
        1,
        "film name",
        "film description",
        "film uri"
    )
) {
    Card {
        Column {
            Text(text = movie.id.toString())
            Text(text = movie.title)
            Text(text = movie.description)
            Text(text = movie.image)
        }
    }
}

@Composable
fun MovieCardPreview() {
    MovieCard()
}