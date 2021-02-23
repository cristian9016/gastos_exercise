package com.example.misgastos.activities.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.misgastos.R
import com.example.misgastos.models.Gasto

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    var data = mutableListOf<Gasto>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MainHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(gasto: Gasto) {
            val gastoValue = view.findViewById<TextView>(R.id.gasto_value)
            val gastoCategory = view.findViewById<TextView>(R.id.gasto_category)
            gastoValue.text = gasto.valor.toString()
            gastoCategory.text = gasto.categoria
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder =
        MainHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.gasto_holder_view, parent, false)
        )

    override fun onBindViewHolder(holder: MainHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}
