package mi2a.kardikoanando.crud_mhs_berita_kardikoanando.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    //http://10.126.148.105/beritaDb/getBerita.php

    private const val Base_URL = "http://10.126.148.105/beritaDb/"

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Base_URL)
            .client(interceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun interceptor() : OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    val beritaService : BeritaService by lazy {
        retrofit.create(BeritaService::class.java)
    }
}