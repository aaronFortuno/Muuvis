package aaronfortuno.ioc.muuvis.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import java.io.InputStream

object ImageUtil {
    fun getPathFromUri(context: Context, uri: Uri): String? {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(uri, proj, null, null, null)
            val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor?.moveToFirst()
            return cursor?.getString(columnIndex!!)
        } finally {
            cursor?.close()
        }
    }

    /*fun chooseImageFromGallery(
        activity: ComponentActivity,
        onImageSelected: (Uri) -> Unit
    ) {
        val pickImage = activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                uri?.let {
                    val inputStream = getInputStreamFromUri(activity, it)
                    onImageSelected(it)
                }
            }
        }
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        pickImage.launch(intent)
    }*/
}

fun getInputStreamFromUri(
    context: Context,
    uri: Uri
) : InputStream? {
    return context.contentResolver.openInputStream(uri)
}

// EXEMPLE D'IMPLEMENTACIÓ A ALTRES CLASSES
/*
fun onChooseImageButtonClicked() {
    chooseImageFromGallery(this) { uri ->
        val path = getPathFromUri(this, uri)
        path?.let {
            // Aquí puedes guardar 'it' en la base de datos
        }
    }
}
*/
