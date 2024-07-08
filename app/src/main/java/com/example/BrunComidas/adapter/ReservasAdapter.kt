package com.example.BrunComidas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.BrunComidas.databinding.ItemReservaBinding
import com.example.BrunComidas.model.login.Reservation

class ReservasAdapter(private var reservas: List<Reservation>) : RecyclerView.Adapter<ReservasAdapter.ReservasViewHolder>() {

    class ReservasViewHolder(private val binding: ItemReservaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reserva: Reservation) {
            binding.tvReservaId.text = reserva.id.toString()
            binding.tvReservaUserId.text = reserva.userId.toString()
            binding.tvReservaRestaurantId.text = reserva.restaurantId.toString()
            binding.tvReservaDate.text = reserva.date
            binding.tvReservaTime.text = reserva.time
            binding.tvReservaPeople.text = reserva.people.toString()
            binding.tvReservaStatus.text = reserva.status
            binding.tvReservaCreatedAt.text = reserva.createdAt
            binding.tvReservaUpdatedAt.text = reserva.updatedAt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservasViewHolder {
        val binding = ItemReservaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservasViewHolder, position: Int) {
        holder.bind(reservas[position])
    }

    override fun getItemCount() = reservas.size

    fun updateReservas(newReservas: List<Reservation>) {
        reservas = newReservas
        notifyDataSetChanged()
    }
}