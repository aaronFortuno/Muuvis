package aaronfortuno.ioc.muuvis.util.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import aaronfortuno.ioc.muuvis.util.api.secret.*

object RetrofitInstance {
    private const val BASE_URL = BaseUrl.retrofit

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}