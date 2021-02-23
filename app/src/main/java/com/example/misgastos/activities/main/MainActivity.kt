package com.example.misgastos.activities.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.misgastos.activities.addgasto.AddGastoActivity
import com.example.misgastos.data.Prefs
import com.example.misgastos.databinding.ActivityMainBinding
import com.example.misgastos.models.Gasto
import com.example.misgastos.models.GastosContainer
import com.example.misgastos.utils.GASTO_EXTRA
import com.example.misgastos.utils.REQUEST_ADD_GASTO
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonParser

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val gastosList = mutableListOf<Gasto>()

    private val mainAdapter = MainAdapter()

    private var total = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        initListeners()
        loadGastos()
    }

    private fun loadGastos() {
        val gastos = Prefs.gastos
        if (gastos.isNotEmpty()) {
            val x = JsonParser.parseString(gastos)
            gastosList.addAll(
                Gson().fromJson(x, GastosContainer::class.java).gastos
            )
            mainAdapter.data = gastosList
            calculateTotal()
        }
    }

    private fun initListeners() {
        binding.btnAddGasto.setOnClickListener {
            goToAddGasto()
        }
    }

    private fun goToAddGasto() {
        startActivityForResult(Intent(this, AddGastoActivity::class.java), REQUEST_ADD_GASTO)
    }

    private fun setupRecyclerView() {
        binding.gastosList.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = mainAdapter
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ADD_GASTO && resultCode == Activity.RESULT_OK) {
            data?.let {
                val gasto = it.extras?.getParcelable(GASTO_EXTRA) as? Gasto
                addGasto(gasto)
            }
        }
    }

    private fun addGasto(gasto: Gasto?) {
        gasto?.let {
            gastosList.add(gasto)
            mainAdapter.data = gastosList
            calculateTotal()
            showSuccess()
        }
        Prefs.gastos = Gson().toJson(GastosContainer(gastosList))
    }

    private fun calculateTotal() {
        total = 0L
        gastosList.map {
            total += it.valor
        }
        binding.total.text = total.toString()
    }

    private fun showSuccess() {
        Snackbar.make(binding.root, "Gasto guardado con exito", Snackbar.LENGTH_SHORT).show()
    }
}
