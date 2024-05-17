package com.example.climatempo.data.remote

import com.example.climatempo.main.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiService {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL) // Use a constante importada
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi: WeatherApi = retrofit.create(WeatherApi::class.java)
}

