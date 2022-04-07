package com.example.appexamccp.webService

import com.example.appexamccp.common.constants.Constants
import com.example.appexamccp.webService.api.UserApi
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class WebServiceClient {
    private var retrofit: Retrofit? = null

    fun getUserssApi(): UserApi{
        return getClient()!!.create(
            UserApi::class.java
        )
    }

    private fun getClient(): Retrofit? {

        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer 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")
                    .build()
                chain.proceed(newRequest)
            }.build()


            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}