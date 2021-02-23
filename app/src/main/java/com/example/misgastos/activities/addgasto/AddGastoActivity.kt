package com.example.misgastos.activities.addgasto

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.misgastos.databinding.ActivityAddGastoBinding
import com.example.misgastos.models.Gasto
import com.example.misgastos.utils.GASTO_EXTRA
import com.example.misgastos.utils.valueOrNull
import com.google.android.material.snackbar.Snackbar

class AddGastoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddGastoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGastoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Agrega tu gasto"
        initListeners()
    }

    private fun initListeners() {
        binding.btnSaveGasto.setOnClickListener {
            val valor = binding.addValorGasto.text.toString().valueOrNull()
            valor?.let {
                val category = binding.addValorCategory.selectedItem.toString()
                saveGasto(it.toLong(), category)
            } ?: showError()
        }
    }

    private fun saveGasto(value: Long, category: String) {
        setResult(
            Activity.RESULT_OK,
            Intent().apply { putExtra(GASTO_EXTRA, Gasto(value, category)) }
        )
        finish()
    }

    private fun showError() {
        Snackbar.make(binding.root, "Valor invalido", Snackbar.LENGTH_SHORT).show()
    }
}
