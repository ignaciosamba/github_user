package com.sambataro.ignacio.anwbassesment.data

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sambataro.ignacio.anwbassesment.data.network.ConnectivityInterceptor
import com.sambataro.ignacio.anwbassesment.data.network.response.CurrentUserReposResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


//https://api.github.com/users/JakeWharton
const val baseUrl : String = "https://api.github.com/"

interface ApiCurrentUser {

    @GET("/users/{name}/repos")
    fun getCurrentUserInfo(@Path("name") name : String) : Deferred<List<CurrentUserReposResponse>>


    @GET()
    fun getCurrentEvents()

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ) : ApiCurrentUser {
            val requestInterceptor =  Interceptor{ chain ->

                val url = chain.request()
                    .url()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                Log.d("SAMBALOIDE", "REQUES IS: " + request.body().toString())

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiCurrentUser::class.java)
        }
    }
}