package com.example.misgastos.activities.login

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.misgastos.activities.main.MainActivity
import com.example.misgastos.data.Prefs
import com.example.misgastos.databinding.ActivityLoginBinding
import com.example.misgastos.utils.valueOrNull
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val controller: LoginController by lazy { LoginController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Prefs.isLogged) {
            goToMain()
        }
        initListeners()
    }

    private fun initListeners() {
        binding.button.setOnClickListener {
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

            val username = binding.editTextTextPersonName.text.toString().valueOrNull()
            val password = binding.editTextTextPersonName2.text.toString().valueOrNull()
            username?.let {
                password?.let {
                    if (controller.login(username, password)) {
                        goToMain()
                    } else {
                        showError()
                    }
                } ?: showError()
            } ?: showError()
        }
    }

    private fun showError() {
        Snackbar.make(binding.root, "Credenciales invalidas", Snackbar.LENGTH_SHORT).show()
    }

    private fun goToMain() {
        Prefs.isLogged = true
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
