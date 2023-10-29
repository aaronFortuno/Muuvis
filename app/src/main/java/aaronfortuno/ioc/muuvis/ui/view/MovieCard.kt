package aaronfortuno.ioc.muuvis.ui.view

import aaronfortuno.ioc.muuvis.data.entity.MovieEntity
import aaronfortuno.ioc.muuvis.ui.theme.MuuvisTheme
import aaronfortuno.ioc.muuvis.ui.view.dialogs.RemoveMovieDialog
import aaronfortuno.ioc.muuvis.ui.viewmodel.MovieViewModel
import aaronfortuno.ioc.muuvis.util.image.CoilImageComponent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieCard(
    movie: MovieEntity = MovieEntity(
        1,
        "film name",
        "film description",
        "ic_launcher_background"
    ),
    viewModel: MovieViewModel
) {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        RemoveMovieDialog(
            movie = movie,
            onDismiss = { showDialog = false },
            viewModel = viewModel)
    }
    Card(
        border = BorderStroke(1.dp, Color.Gray),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(shape = RoundedCornerShape(8.dp))
            .shadow(3.dp)
            .combinedClickable(
                onClick = { /* TODO open card */ },
                onLongClick = { showDialog = true }
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CoilImageComponent(
                imageUrl = movie.imageUrl,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black.copy(alpha = 0.4F))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White,
                        )
                        Text(
                            text = movie.description,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun MovieCardPreview() {
    MuuvisTheme {
        // MovieCard()
    }
}