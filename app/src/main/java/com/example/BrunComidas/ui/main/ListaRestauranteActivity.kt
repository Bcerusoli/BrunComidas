package com.example.BrunComidas.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.BrunComidas.R
import com.example.BrunComidas.adapter.RestauranteAdapter
import com.example.BrunComidas.databinding.ActivityListaRestauranteBinding

import com.example.BrunComidas.model.login.Restaurant
import com.example.BrunComidas.ui.viewModel.ListaRestauranteViewModel

class ListaRestauranteActivity : AppCompatActivity(), RestauranteAdapter.OnRestaurantClickListener{
    lateinit var binding: ActivityListaRestauranteBinding
    private val model: ListaRestauranteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListaRestauranteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
        setupViewModelObservers()
        model.fetchListaRestaurantes()
    }

    private fun setupRecyclerView() {
        binding.lstRestaurantes.apply {
            layoutManager = LinearLayoutManager(this@ListaRestauranteActivity)
            adapter = RestauranteAdapter(arrayListOf(),this@ListaRestauranteActivity)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cerrar_sesion -> {
                val sessionManager = SessionManager(this)
                sessionManager.isUserLoggedIn = false
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Finaliza la actividad actual para evitar volver a ella al presionar el botón de retroceso
                true
            }
            R.id.action_registrarse -> {
                val intent = Intent(this, RegistroActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_iniciar_sesion -> {
                Toast.makeText(this, "Función de inicio de sesión no implementada", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_ver_restaurantes -> {
                val intent = Intent(this, ListaRestauranteActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun setupViewModelObservers() {
        model.restaurantes.observe(this){
            val lst = binding.lstRestaurantes
            lst.adapter = RestauranteAdapter(it,this)
        }
    }

    override fun onRestaurantClick(restaurant: Restaurant) {
        val intent = Intent(this, RestaurantDetailActivity::class.java)
        intent.putExtra("restaurantId", restaurant.id)
        startActivity(intent)
    }
}