package com.example.climatempo.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("name")
    val name: String, // Nome da cidade

    @SerializedName("main")
    val main: MainWeatherData, // Dados principais do clima

    @SerializedName("weather")
    val weather: List<WeatherDescription> // Lista de descrições do clima
)

data class MainWeatherData(
    @SerializedName("temp")
    val temp: Double // Temperatura em graus Celsius
)

data class WeatherDescription(
    @SerializedName("description")
    val description: String, // Descrição do tempo (ex: "nublado")

    @SerializedName("icon") // Código do ícone do tempo (ex: "04d")
    val icon: String
)
