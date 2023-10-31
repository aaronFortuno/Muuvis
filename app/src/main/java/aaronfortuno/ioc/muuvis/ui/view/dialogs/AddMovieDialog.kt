package aaronfortuno.ioc.muuvis.ui.view.dialogs

import aaronfortuno.ioc.muuvis.ui.viewmodel.ImageViewModel
import aaronfortuno.ioc.muuvis.ui.viewmodel.MovieViewModel
import aaronfortuno.ioc.muuvis.util.image.ImagePicker
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMovieDialog(
    onDismiss: () -> Unit,
    viewModel: MovieViewModel
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    // var imageUrl by remember { mutableStateOf("") }
    
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope

    val selectedImageUri = rememberSaveable {
        mutableStateOf<Uri?>(null)
    }

    /*
    val pickImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri.value = uri
    }
    */

    val context = LocalContext.current

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
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("description")}
                )
                ImagePicker {uri ->
                    selectedImageUri.value = uri
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                val imageUri = selectedImageUri.value
                val imageViewModel= ImageViewModel()
                lifecycleScope.launch(Dispatchers.IO) {
                    val imageUrlFromGCS = imageUri?.let { uri ->
                        imageViewModel.uploadImageFromUri(context, uri)
                    } ?: ""
                    viewModel.addMovie(
                        title,
                        genre = "",
                        duration = 0,
                        year = 0,
                        description,
                        rating = 0.0,
                        isWatched = true,
                        imageUrlFromGCS)
                    withContext(Dispatchers.Main) {
                        onDismiss()
                    }
                }
            }) {
                Text(text = "add")
            }
        })
}