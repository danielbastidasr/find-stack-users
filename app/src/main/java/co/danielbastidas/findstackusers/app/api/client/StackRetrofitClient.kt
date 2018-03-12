package co.danielbastidas.findstackusers.app.api.client

import co.danielbastidas.findstackusers.app.api.StackService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.JsonDeserializer
import java.util.*



internal class StackRetrofitClient(baseURL:String) {

    private var retrofit: Retrofit

    init {
        val deserializer: JsonDeserializer<Date> = JsonDeserializer<Date> {
            json, _, _ ->
            if (json == null) null
            else Date(json.asLong*1000)
        }

        val gsonAdapter:Gson = GsonBuilder()
                .registerTypeAdapter(Date::class.java,deserializer)
                .create()

        val okHttpClient = OkHttpClient.Builder().build()
        val builder = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gsonAdapter))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseURL)

        retrofit = builder.build()
    }

    fun getStackService(): StackService {
        return retrofit.create<StackService>(StackService::class.java)
    }


}