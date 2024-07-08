package com.example.BrunComidas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.BrunComidas.R
import com.example.BrunComidas.model.login.Plato


class PlatoAdapter(private var platoList: List<Plato>) : RecyclerView.Adapter<PlatoAdapter.PlatoViewHolder>() {

    inner class PlatoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val platoName: TextView = view.findViewById(R.id.plato_name)
        val platoPrice: TextView = view.findViewById(R.id.plato_price)

        fun bind(plato: Plato) {
            platoName.text = plato.name
            platoPrice.text = plato.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plato_item, parent, false)
        return PlatoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlatoViewHolder, position: Int) {
        val plato = platoList[position]
        holder.bind(plato)
    }

    override fun getItemCount() = platoList.size
}