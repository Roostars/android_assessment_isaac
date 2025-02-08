package com.example.prueba.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.prueba.R
import com.example.prueba.data.model.UiState
import com.example.prueba.databinding.ActivityMainBinding
import com.example.prueba.ui.home.HomeActivity

class MainActivity : AppCompatActivity() {

    val factsViewModel: FactsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler(Looper.getMainLooper()).postDelayed({
            factsViewModel.fetchFacts()
        }, 4000)

        setObserver()
    }

    fun setObserver() {
        factsViewModel.facts.observe(this) { uiState ->
            when (uiState) {
                UiState.Loading -> {
                    //mostrar loader
                }

                is UiState.Error -> {
                    //ocultar loader
                    Toast.makeText(this, uiState.message, Toast.LENGTH_SHORT).show()
                }

                is UiState.Success -> {
                    factsViewModel.saveFacts(uiState.data)
                }
            }
        }

        factsViewModel.saveRoom.observe(this) {
            when (it) {
                UiState.Loading -> Unit

                is UiState.Error -> {
                    //ocultar loader
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                is UiState.Success -> {
                    //ocultar loader
                    //navegar a vista
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}