package co.danielbastidas.findstackusers.app.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class StackClient {

    private val BASE_URL = "https://api.stackexchange.com//2.2/"
    private var retrofit: Retrofit

    init {
        val okHttpClient = OkHttpClient.Builder().build()
        val builder = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)

        retrofit = builder.build()
    }

    fun getStackService(): StackService {
        return retrofit.create<StackService>(StackService::class.java)
    }
}