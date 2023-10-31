package aaronfortuno.ioc.muuvis.data.repository

import aaronfortuno.ioc.muuvis.util.api.RetrofitInstance
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import java.io.File

class ImageRepository {
    fun uploadImage(file: File): Call<ResponseBody> {
        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("image", file.name, requestBody)

        return RetrofitInstance.apiService.uploadImage(body)
    }
}