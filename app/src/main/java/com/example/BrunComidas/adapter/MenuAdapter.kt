package com.example.BrunComidas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.BrunComidas.R

import com.example.BrunComidas.model.login.Menu
import com.example.BrunComidas.ui.viewModel.MenuViewModel


class MenuAdapter(private var menuList: List<Menu>, private val viewModel: MenuViewModel) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val menuName: TextView = view.findViewById(R.id.menu_name)
        val platoRecyclerView: RecyclerView = view.findViewById(R.id.plato_recycler_view)

        fun bind(menu: Menu) {
            menuName.text = menu.name
            platoRecyclerView.layoutManager = LinearLayoutManager(view.context)
            val platoAdapter = PlatoAdapter(menu.plates)
            platoRecyclerView.adapter = platoAdapter
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menuList[position]
        holder.bind(menu)
    }

    override fun getItemCount() = menuList.size

    fun updateMenu(newMenu: List<Menu>) {
        menuList = newMenu
        notifyDataSetChanged() // Notify the RecyclerView that the data has changed
    }
}