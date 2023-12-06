package com.example.infinitymoneymanager.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Redeem
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class Category(
    val name : String,
    val icon : ImageVector
)

val DefaultSpendingCategories = listOf(
    Category(
        "Alimentação",
        Icons.Filled.Restaurant
    ),
    Category(
        "Saúde",
        Icons.Filled.HealthAndSafety
    ),
    Category(
        "Educação",
        Icons.Filled.School
    ),
    Category(
        "Meta",
        Icons.Filled.Flag
    ),
    Category(
        "Transporte",
        Icons.Filled.DirectionsCar
    ),
    Category(
        "Exercício",
        Icons.Filled.FitnessCenter
    ),
    Category(
        "Presentes",
        Icons.Filled.Redeem
    ),
    Category(
        "Mercado",
        Icons.Filled.ShoppingCart
    ),
    Category(
        "Casa",
        Icons.Filled.House
    ),
    Category(
        "Outros",
        Icons.Filled.QuestionMark
    )
)

val DefaultRevenuesCategories = listOf(
    Category(
        "Salário",
        Icons.Filled.Payments
    ),
    Category(
        "Investimentos",
        Icons.Filled.AccountBalance
    ),
    Category(
        "Presente",
        Icons.Filled.Redeem
    ),
    Category(
        "Outros",
        Icons.Filled.QuestionMark
    )
)
