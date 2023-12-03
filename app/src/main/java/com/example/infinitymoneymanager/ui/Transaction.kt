package com.example.infinitymoneymanager.ui

data class Transaction(
    val id: String,
    val category: String,
    val description: String,
    val value: Double,
    val date: String,
    val periodicity: String,
)