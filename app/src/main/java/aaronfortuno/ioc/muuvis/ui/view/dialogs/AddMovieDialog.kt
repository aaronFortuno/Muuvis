package aaronfortuno.ioc.muuvis.ui.view.dialogs

import aaronfortuno.ioc.muuvis.ui.viewmodel.MovieViewModel
import aaronfortuno.ioc.muuvis.util.ImagePicker
import aaronfortuno.ioc.muuvis.util.ImageUtil
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
    var imageUrl by remember { mutableStateOf("") }
    
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope

    val selectedImageUri = rememberSaveable {
        mutableStateOf<Uri?>(null)
    }

    val pickImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri.value = uri
    }
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
                val finalImagePath = selectedImageUri.value?.let { ImageUtil.getPathFromUri(context, it) }
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.addMovie(title, description, finalImagePath ?: "")
                }
            }) {
                Text(text = "add")
            }
        })
}