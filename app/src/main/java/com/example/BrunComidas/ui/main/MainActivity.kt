package com.example.BrunComidas.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem

import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.BrunComidas.R
import com.example.BrunComidas.databinding.ActivityMainBinding
import com.example.BrunComidas.ui.viewModel.MainViewModel


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Check if user is already logged in
        val sessionManager = SessionManager(this)
        if (sessionManager.isUserLoggedIn) {
            val intent = Intent(this, ListaRestauranteActivity::class.java)
            startActivity(intent)
            finish() // Finish MainActivity so user can't go back to it
            return
        }

        acionarBotonAgregar()

        // Modifica el observador de errorMessage
        model.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage.isEmpty() && model.isLoginAttempted) {
                Toast.makeText(this, "Usuario inició sesión correctamente", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListaRestauranteActivity::class.java)
                startActivity(intent)
            } else if (model.isLoginAttempted) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
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
    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_item, menu)
        return true
    }


    private fun acionarBotonAgregar() {
        binding.signInBtn.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
        binding.loginBtn.setOnClickListener {
            binding.emileTxt.visibility = View.VISIBLE
            binding.passwordTxt.visibility = View.VISIBLE
            val email = binding.emileTxt.text.toString()
            val password = binding.passwordTxt.text.toString()
            model.login(email, password, this)
        }
        binding.guestBtn.setOnClickListener {
            val intent = Intent(this, ListaRestauranteActivity::class.java)
            startActivity(intent)
        }
    }
}