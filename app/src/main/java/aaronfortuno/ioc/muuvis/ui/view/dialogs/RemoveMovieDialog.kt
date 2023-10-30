package aaronfortuno.ioc.muuvis.ui.view.dialogs

import aaronfortuno.ioc.muuvis.data.entity.MovieEntity
import aaronfortuno.ioc.muuvis.ui.viewmodel.MovieViewModel
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        text = { 
               Text(text = "be careful, this can't be undone!")
               },
        confirmButton = {
            Button(onClick = {
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.deleteMovie(movie)
                    withContext(Dispatchers.Main) {
                        onDismiss()
                    }
                }
            }) {
                Text(text = "DELETE")
            }
        })
}