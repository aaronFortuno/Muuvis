package aaronfortuno.ioc.muuvis.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

object ImageUtil {

    fun Context.getPathFromUri(uri: Uri): String? {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = contentResolver.query(uri, proj, null, null, null)
            val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor?.moveToFirst()
            return cursor?.getString(columnIndex!!)
        } finally {
            cursor?.close()
        }
    }

    fun chooseImageFromGallery(
        activity: ComponentActivity,
        onImageSelected: (Uri) -> Unit
    ) {
        val pickImage = activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                uri?.let {
                    onImageSelected(it)
                }
            }
        }
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        pickImage.launch(intent)
    }
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

// CODI IMPORTAT TODO adaptar
/**
 * Funció per obtenir la ruta de l'arxiu a partir de la Uri que passem
 *
 * @param context context de l'aplicació
 * @param uri Uri de la imatge que hem triat
 *
 * @return si s'escau, cadena de text amb la Uri del recurs, per emmagatzemar-ho a la DB
 */
/*private fun getPathFromUri(context: Context, uri: Uri): String? {
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
}*/


/**
 * Valor privat per gestionar la possibilitat d'afegir imatges de la galeria,
 * amb un ActivityResultContract
 */
/*
private val pickImage = registerForActivityResult(
    ActivityResultContracts
        .StartActivityForResult()
) { result ->
    if (result.resultCode == Activity.RESULT_OK) {
        val uri = result.data?.data
        uri?.let {
            try {
                contentResolver.takePersistableUriPermission(
                    it,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            } catch (e: Exception) {
                // TODO: GESTIONAR L'EXCEPCIÓ
            }

            selectedImageUri = it
            getPathFromUri(this, it)
            editBookImage.setImageURI(it)
        }
    }
}*/
