package com.shaadi.demo.utils

import android.util.Log
import com.google.gson.GsonBuilder
import com.shaadi.demo.BuildConfig
import com.shaadi.demo.config.AppConfig
import com.shaadi.demo.config.AppConfig.IO_TIMEOUT
import com.shaadi.demo.model.Profile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object Retrofit {

    @Provides
    @Singleton
    fun getClient(baseUrl: String = AppConfig.API_ENDPOINT): Retrofit {

        val gson = GsonBuilder()
            .registerTypeAdapter(Profile::class.java, ProfileTypeAdapter())
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    fun Throwable.printRetrofitError() {
        if (BuildConfig.DEBUG) {
            this.printStackTrace()
            when (this) {
                is IOException -> Log.e(
                    this::class.java.simpleName,
                    "Network Error happened in Retrofit | cause: ${this.cause} | message: ${this.message}"
                )
                is HttpException -> Log.e(
                    this::class.java.simpleName,
                    "HTTP Exception happened in Retrofit | cause: ${this.cause} | message: ${this.message}"
                )
                else -> Log.e(
                    this::class.java.simpleName,
                    "Unknown Error happened in Retrofit | cause: ${this.cause} | message: ${this.message}"
                )
            }
        }
    }
}