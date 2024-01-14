package com.example.amphibiansapp.network

import com.example.weatherforcast.model.Weather
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val Base_Url = "https://api.openweathermap.org/"
private const val API_KEY = "ed60fcfbd110ee65c7150605ea8aceea"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(Base_Url)
    .build()

interface AppApiService{

    @GET("data/2.5/forecast/daily")
     suspend fun getFullData(
        @Query("q") query: String,
        @Query("units") units : String = "imperial",
        @Query("appid") appid : String = API_KEY
     ) : Weather

}

object AmphibiansAppApi{
    val retrofitService : AppApiService by lazy{
        retrofit.create(AppApiService::class.java)
    }

}