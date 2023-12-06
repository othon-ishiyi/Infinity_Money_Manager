package com.example.infinitymoneymanager.ui

import java.sql.Date

data class Transaction(
    val id: String,
    val category: String,
    val description: String,
    val value: Double,
    val date: String,
    val periodicity: String,
)
data class Metaa(
    val id: Int = 0,
    val nome: String = "",
    val valorAlvo: Double = 0.0,
    val valorArrecadado: Double = 0.0,
    val prazo: Date = Date(0)
)