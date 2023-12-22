package com.core.network.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "55368e778ff6a07240912cea58f157a7"

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideRetrofit(
        authOkHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(authOkHttpClient)
            .build()
    }

    @Provides
    fun provideAuthInterceptorOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val url: HttpUrl =
                    chain.request().url.newBuilder()
                        .addQueryParameter("api_key", API_KEY)
                        .build()
                val request: Request =
                    chain.request().newBuilder().url(url).build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    fun provideGson(): Gson = Gson()
}