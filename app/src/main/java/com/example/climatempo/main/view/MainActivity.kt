package com.example.climatempo.main.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.climatempo.R
import com.example.climatempo.main.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var etCityName: EditText
    private lateinit var btnGetWeather: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var tvCityName: TextView
    private lateinit var tvTemperature: TextView
    private lateinit var tvDescription: TextView
    private lateinit var ivWeatherIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme) // Aplicar o tema manualmente
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCityName = findViewById(R.id.etCityName)
        btnGetWeather = findViewById(R.id.btnGetWeather)
        progressBar = findViewById(R.id.progressBar)
        tvCityName = findViewById(R.id.tvCityName)
        tvTemperature = findViewById(R.id.tvTemperature)
        tvDescription = findViewById(R.id.tvDescription)
        ivWeatherIcon = findViewById(R.id.ivWeatherIcon) // Referência para o ImageView

        btnGetWeather.setOnClickListener {
            val cityName = etCityName.text.toString().trim()
            if (cityName.isNotEmpty()) {
                viewModel.getWeather(cityName)
            } else {
                Toast.makeText(this, "Por favor, digite o nome da cidade", Toast.LENGTH_SHORT).show()
            }
        }q

                viewModel.weatherData.observe(this) { weatherResponse ->
                    if (weatherResponse != null) {
                        tvCityName.text = weatherResponse.name
                        tvTemperature.text = "${weatherResponse.main.temp}°C"
                        tvDescription.text = weatherResponse.weather[0].description
                        // Lógica para exibir o ícone do clima com base em weatherResponse.weather[0].icon
                    } else {
                        // Lógica para lidar com erro (ex: exibir mensagem de erro)
                    }
                }

        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            btnGetWeather.isEnabled = !isLoading
        }

        viewModel.errorMessage.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}
