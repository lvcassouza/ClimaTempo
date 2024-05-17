package com.example.climatempo.data.model

import com.google.gson.annotations.SerializedName // Importe a anotação @SerializedName

data class WeatherResponse(
    @SerializedName("name") // Nome da cidade
    val name: String,

    @SerializedName("main") // Objeto "main" contendo temperatura, etc.
    val main: MainWeatherData,

    @SerializedName("weather") // Lista de objetos "weather" com descrição, etc.
    val weather: List<WeatherDescription>
)

data class MainWeatherData(
    @SerializedName("temp") // Temperatura em graus Celsius
    val temp: Double
)

data class WeatherDescription(
    @SerializedName("description") // Descrição do tempo (ex: "nublado")
    val description: String
)
