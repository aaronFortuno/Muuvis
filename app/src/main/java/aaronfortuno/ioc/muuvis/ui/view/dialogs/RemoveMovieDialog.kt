package aaronfortuno.ioc.muuvis.ui.view.dialogs

import aaronfortuno.ioc.muuvis.data.entity.MovieEntity
import aaronfortuno.ioc.muuvis.ui.viewmodel.MovieViewModel
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RemoveMovieDialog(
    movie: MovieEntity,
    onDismiss: () -> Unit,
    viewModel: MovieViewModel
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "remove movie") },
        text = { "this can't be undone!" },
        confirmButton = {
            Button(onClick = {
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.deleteMovie(movie)
                }
            }) {
                Text(text = "DELETE")
            }
        })
}