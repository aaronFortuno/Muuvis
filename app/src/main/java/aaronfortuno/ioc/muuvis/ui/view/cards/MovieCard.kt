package aaronfortuno.ioc.muuvis.ui.view.cards

import aaronfortuno.ioc.muuvis.data.entity.MovieEntity
import aaronfortuno.ioc.muuvis.ui.theme.MuuvisTheme
import aaronfortuno.ioc.muuvis.ui.view.dialogs.RemoveMovieDialog
import aaronfortuno.ioc.muuvis.ui.viewmodel.MovieViewModel
import aaronfortuno.ioc.muuvis.util.image.CoilImageComponent
import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieCard(
    movie: MovieEntity,
    viewModel: MovieViewModel
) {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        RemoveMovieDialog(
            movie = movie,
            onDismiss = { showDialog = false },
            viewModel = viewModel
        )
    }

    val shape = RectangleShape

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val cardExpanded = remember { mutableStateOf(false) }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val targetCardWidth: Dp
    val targetCardHeight: Dp

    if (isLandscape) {
        targetCardWidth = if (cardExpanded.value) screenWidth else screenHeight * 9f / 14f
        targetCardHeight = screenHeight
    } else {
        targetCardWidth = screenWidth
        targetCardHeight = if (cardExpanded.value) screenHeight else screenWidth * 9f / 14f
    }

    val cardWidth = animateDpAsState(targetValue = targetCardWidth, label = "")
    val cardHeight = animateDpAsState(targetValue = targetCardHeight, label = "")
    if (!isLandscape) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                shape = shape,
                modifier = Modifier
                    .width(cardWidth.value)
                    .height(cardHeight.value)
                    .fillMaxWidth()
                    .combinedClickable(
                        onClick = { cardExpanded.value = !cardExpanded.value },
                        onLongClick = { showDialog = true }
                    )
            ) {
                CardContent(movie, viewModel)
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Card(
                shape = shape,
                modifier = Modifier
                    .width(cardWidth.value)
                    .height(cardHeight.value)
                    .fillMaxHeight()
                    .combinedClickable(
                        onClick = { cardExpanded.value = !cardExpanded.value },
                        onLongClick = { showDialog = true }
                    )
            ) {
                CardContent(movie, viewModel)
            }
        }
    }
}

@Composable
fun CardContent(
    movie: MovieEntity,
    viewModel: MovieViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        RemoveMovieDialog(
            movie = movie,
            onDismiss = { showDialog = false },
            viewModel = viewModel
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .wrapContentSize(Alignment.TopStart)
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
        IconButton(
            onClick = { expanded = true },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.2f))
        ) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "more actions"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .clickable { expanded = true }
            /*                    .align(Alignment.TopEnd)
                                .offset { IntOffset(-40, -40) }*/
        ) {
            DropdownMenuItem(
                onClick = {
                    showDialog = true
                    expanded = false
                },
                text = {
                    Text(text = "delete movie")
                }
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun AlternativeMovieCardPreview() {
    MuuvisTheme {
        // AlternativeMovieCard()
    }
}