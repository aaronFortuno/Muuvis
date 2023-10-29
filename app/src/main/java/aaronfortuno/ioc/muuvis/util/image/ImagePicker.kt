package aaronfortuno.ioc.muuvis.util.image

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ImagePicker(
    onImageSelected: (Uri?) -> Unit
) {
    val pickImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        onImageSelected(uri)
    }
    Button(onClick = {
        pickImage.launch("image/*")
    }) {
        Text(text = "pick an image from gallery")
    }
}