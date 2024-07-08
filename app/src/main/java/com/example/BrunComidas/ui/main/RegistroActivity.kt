package com.example.BrunComidas.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.BrunComidas.R
import com.example.BrunComidas.databinding.ActivityRegistroBinding
import com.example.BrunComidas.ui.viewModel.RegistroViewModel

class RegistroActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistroBinding
    private val model: RegistroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViewModelObservers()

        getRegistroFromInput()
    }

    private fun setupViewModelObservers() {
        model.closeActivity.observe(this) {
            if (it) {
                finish()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cerrar_sesion -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
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


    fun getRegistroFromInput() {
        binding.RRegistroBtn.setOnClickListener {
            val nombre = binding.RNombreTxt.text.toString()
            val email = binding.REmileTxt.text.toString()
            val password = binding.RPasswordTxt.text.toString()
            val numero = binding.RNumeroTxt.text.toString()
            model.createRegistro(nombre, email, password, numero)
        }

    }


}