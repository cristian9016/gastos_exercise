package com.example.misgastos.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gasto(
    val valor: Long,
    val categoria: String
) : Parcelable
