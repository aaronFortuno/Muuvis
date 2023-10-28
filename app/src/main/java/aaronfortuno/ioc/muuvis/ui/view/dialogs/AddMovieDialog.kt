package aaronfortuno.ioc.muuvis.ui.view.dialogs

import aaronfortuno.ioc.muuvis.ui.viewmodel.MovieViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMovieDialog(
    onDismiss: () -> Unit,
    viewModel: MovieViewModel
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "add movie") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("title")}
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { title = it },
                    label = { Text("description")}
                )
                OutlinedTextField(
                    value = imageUrl,
                    onValueChange = { title = it },
                    label = { Text("image url")}
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                viewModel.addMovie(title, description, imageUrl)
            }) {

            }
        })
}