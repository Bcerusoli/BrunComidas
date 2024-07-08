package com.example.BrunComidas.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.BrunComidas.adapter.ReservasAdapter
import com.example.BrunComidas.databinding.ActivityReservasBinding
import com.example.BrunComidas.ui.viewModel.ReservasViewModel
import androidx.activity.viewModels

class ReservasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReservasBinding
    private val viewModel: ReservasViewModel by viewModels()
    private lateinit var reservasAdapter: ReservasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa tu RecyclerView
        binding.reservasRecyclerView.layoutManager = LinearLayoutManager(this)
        reservasAdapter = ReservasAdapter(emptyList())
        binding.reservasRecyclerView.adapter = reservasAdapter

        // Configura tu ViewModel
        viewModel.reservas.observe(this, { reservas ->
            // Actualiza tu RecyclerView con las nuevas reservas
            reservasAdapter.updateReservas(reservas)
        })

        viewModel.getReservations() // Asegúrate de tener un método getReservas() en tu ViewModel que actualice las reservas
    }
}