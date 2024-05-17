package com.example.climatempo.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.climatempo.data.model.WeatherResponse
import com.example.climatempo.data.remote.WeatherApiService
import com.example.climatempo.main.Constants
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> = _weatherData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getWeather(city: String) {
        _isLoading.value = true
        viewModelScope.launch {
            WeatherApiService.weatherApi.getWeather(city, Constants.API_KEY).enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _weatherData.postValue(response.body())
                    } else {
                        _errorMessage.postValue("Erro ao buscar dados do clima: ${response.code()}")
                        Log.e("MainViewModel", "Erro na chamada da API: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    _isLoading.value = false
                    _errorMessage.postValue("Erro de rede: ${t.message}")
                    Log.e("MainViewModel", "Erro de rede: ${t.message}", t)
                }
            })
        }
    }
}

