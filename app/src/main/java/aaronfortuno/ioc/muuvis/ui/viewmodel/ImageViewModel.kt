package aaronfortuno.ioc.muuvis.ui.viewmodel

import aaronfortuno.ioc.muuvis.data.repository.ImageRepository
import androidx.lifecycle.ViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import java.io.File

class ImageViewModel : ViewModel() {
    private val repository = ImageRepository()

    fun uploadImage(file: File): Call<ResponseBody> {
        return repository.uploadImage(file)
    }
}