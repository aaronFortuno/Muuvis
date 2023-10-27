package aaronfortuno.ioc.muuvis

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


@Composable
fun MuuvisApp() {
    MovieList()
}

@Preview(showBackground = true)
@Composable
fun MuuvisPreview() {
    MuuvisTheme {
        MuuvisApp()
    }
}