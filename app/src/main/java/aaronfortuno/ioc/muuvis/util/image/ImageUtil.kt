package aaronfortuno.ioc.muuvis.util.image

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

object ImageUtil {

    fun InputStream.copyTo(out: OutputStream, bufferSize: Int = DEFAULT_BUFFER_SIZE): Long {
        var bytesCopied: Long = 0
        val buffer = ByteArray(bufferSize)
        var bytes = read(buffer)
        while (bytes >= 0) {
            out.write(buffer, 0, bytes)
            bytesCopied += bytes
            bytes = read(buffer)
        }
        return bytesCopied
    }
    fun uriToFile(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.cacheDir, "tempImage.jpg")
        inputStream?.use {
            val outputStream = FileOutputStream(file)
            it.copyTo(outputStream)
        }
        return file
    }


    /*fun getPathFromUri(context: Context, uri: Uri): String? {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(uri, proj, null, null, null)
            val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor?.moveToFirst()
            Log.d("ImageUtil.getPathFromUri", "cursor: ${cursor?.getString(columnIndex!!)}")
            return cursor?.getString(columnIndex!!)
        } finally {
            cursor?.close()
        }
    }*/

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
