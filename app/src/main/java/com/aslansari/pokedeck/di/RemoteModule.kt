package com.aslansari.pokedeck.di

import android.content.Context
import com.aslansari.pokedeck.BuildConfig
import com.aslansari.pokedeck.network.PokemonService
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

const val DISK_CACHE_SIZE = 10 * 1024 * 1024 // 10MB
const val CACHE_DURATION_MINUTES = 10L

/**
 * This module contains singleton providers for remote service needs
 */
@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @LoggingInterceptor
    fun provideLoggingInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        return httpLoggingInterceptor
    }

    @Provides
    fun provideChuckerInterceptor(
        @ApplicationContext context: Context,
        chuckerCollector: ChuckerCollector,
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .alwaysReadResponseBody(true)
            .build()
    }

    @Provides
    fun provideChuckerCollector(
        @ApplicationContext context: Context,
    ): ChuckerCollector {
        return ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
    }

    @Provides
    fun provideRetrofit(
        @ApplicationContext context: Context,
        @LoggingInterceptor loggingInterceptor: Interceptor,
        chuckerInterceptor: ChuckerInterceptor,
        gson: Gson,
    ): Retrofit {
        // Install an HTTP cache in the application cache directory.
        var cache: Cache? = null
        // Install an HTTP cache in the application cache directory.
        try {
            val cacheDir = File(context.cacheDir, "apiResponses")
            cache = Cache(cacheDir, DISK_CACHE_SIZE.toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
//            .cache(cache)
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create(gson))
            client(okHttpClient)
            baseUrl(BuildConfig.API_BASE_URL)
        }.build()

        return retrofit
    }

    @Provides
    fun providePokemonService(retrofit: Retrofit): PokemonService {
        return retrofit.create(PokemonService::class.java)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptor
