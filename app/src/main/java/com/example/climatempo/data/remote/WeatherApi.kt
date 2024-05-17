package com.example.climatempo.data.remote

import com.example.climatempo.data.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather") // Substitua pelo endpoint correto da sua API de clima
    fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric" // Ou "imperial"
    ): Call<WeatherResponse> // Corrigido: tipo de retorno Call<WeatherResponse>
}
