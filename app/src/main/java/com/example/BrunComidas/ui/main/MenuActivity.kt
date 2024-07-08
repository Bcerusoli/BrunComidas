package com.example.BrunComidas.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.BrunComidas.R
import com.example.BrunComidas.adapter.MenuAdapter
import com.example.BrunComidas.model.login.Menus
import com.example.BrunComidas.ui.viewModel.MenuViewModel

class MenuActivity : AppCompatActivity() {
    private val viewModel: MenuViewModel by viewModels()
    private lateinit var menuAdapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_recycler_view)

        val restaurantId = intent.getLongExtra("restaurant_id", 0)
        println("Received Restaurant ID: $restaurantId")

        viewModel.getRestaurantMenu(restaurantId)

        // Initialize RecyclerView and Adapter
        val recyclerView = findViewById<RecyclerView>(R.id.menu_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        menuAdapter = MenuAdapter(emptyList(), viewModel) // Pass viewModel to MenuAdapter
        recyclerView.adapter = menuAdapter

        viewModel.menu.observe(this, { menu: Menus? ->
            // Update your UI with the menu
            menu?.let {
                menuAdapter.updateMenu(it) // Update the existing MenuAdapter
            }
        })
    }
}