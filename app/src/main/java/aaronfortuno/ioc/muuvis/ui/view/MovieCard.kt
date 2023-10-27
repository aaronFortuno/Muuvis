package aaronfortuno.ioc.muuvis.ui.view

import aaronfortuno.ioc.muuvis.data.entity.MovieEntity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MovieCard(
    movie: MovieEntity = MovieEntity(
        1,
        "film name",
        "film description",
        "film uri"
    )
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(shape = RoundedCornerShape(8.dp))
    ) {
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun MovieCardPreview() {
    MovieCard()
}