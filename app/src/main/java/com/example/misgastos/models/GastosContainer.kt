package com.example.misgastos.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class GastosContainer(
    val gastos: MutableList<Gasto>
) : Parcelable
