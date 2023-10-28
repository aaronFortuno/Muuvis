package aaronfortuno.ioc.muuvis

import aaronfortuno.ioc.muuvis.data.entity.MovieEntity
import aaronfortuno.ioc.muuvis.data.repository.MovieRepositoryImpl
import aaronfortuno.ioc.muuvis.ui.view.MovieList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import aaronfortuno.ioc.muuvis.ui.theme.MuuvisTheme
import aaronfortuno.ioc.muuvis.ui.viewmodel.MovieViewModel
import aaronfortuno.ioc.muuvis.ui.viewmodel.ViewModelFactory
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private val repository = MovieRepositoryImpl(App.db.movieDao())
    private val viewModel by viewModels<MovieViewModel> {
        ViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuuvisTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MuuvisApp()
                }
            }
        }
    }
}

val sampleMovies = listOf(
    MovieEntity(1, "Pelicula 1", "Descripción 1", "film1_davinci"),
    MovieEntity(2, "Pelicula 2", "Descripción 2", "film2_picasso"),
    MovieEntity(3, "Pelicula 3", "Descripción 3", "film3_manet")
)
@Composable
fun MuuvisApp() {
    val showDialog = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MovieList(sampleMovies)

        // FAB
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { showDialog.value = true }
        ){
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "add movie"
            )
        }
    }

    if (showDialog.value) {

    }
}

@Preview(showBackground = true)
@Composable
fun MuuvisPreview() {
    MuuvisTheme {
        MuuvisApp()
    }
}