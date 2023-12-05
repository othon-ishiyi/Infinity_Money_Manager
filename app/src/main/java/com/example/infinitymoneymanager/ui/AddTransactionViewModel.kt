package com.example.infinitymoneymanager.ui

data class Recurrence(
    val unique: Boolean,
    val type: String,
    val name: String
)

val recurrenceOptions = listOf(
    Recurrence(
        true,"unico", "Único"
    ),
    Recurrence(
        false, "semanal", "Semanal"
    ),
    Recurrence(
        false, "mensal", "Mensal"
    ),
    Recurrence(
        false, "anual", "Anual"
    )
)
class AddTransactionViewModel {
}