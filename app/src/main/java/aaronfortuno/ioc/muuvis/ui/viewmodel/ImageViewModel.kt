package aaronfortuno.ioc.muuvis.ui.viewmodel

import aaronfortuno.ioc.muuvis.data.repository.ImageRepository
import aaronfortuno.ioc.muuvis.util.image.ImageUtil
import aaronfortuno.ioc.muuvis.util.image.calculateHash
import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import java.io.File

class ImageViewModel : ViewModel() {
    private val repository = ImageRepository()

    private fun uploadImage(file: File): Call<ResponseBody> {
        return repository.uploadImage(file)
    }

    fun uploadImageFromUri(context: Context, uri: Uri): String {
        val file = ImageUtil.uriToFile(context, uri)
        val hashName = calculateHash(file) + ".jpg"
        val renamedFile = File(file.parentFile, hashName)
        file.renameTo(renamedFile)
        val response = uploadImage(renamedFile).execute()
        val imageUrlFromGCS = response.body()?.let {
            "https://storage.googleapis.com/muuvis_images/$hashName"
        } ?: "no image"
        return imageUrlFromGCS
    }
}