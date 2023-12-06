package com.example.infinitymoneymanager.ui

import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Error
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.infinitymoneymanager.databaseClasses.revenues
import com.example.infinitymoneymanager.databaseClasses.spendings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch
data class CompositionUiState(
    val isSpending: Boolean = true,
    val currentSearch: String = ""
)

class CompositionViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(CompositionUiState())
    val uiState: StateFlow<CompositionUiState> = _uiState.asStateFlow()

    private val _transactions = MutableLiveData<List<Transaction>>()
    val transactions: List<Transaction>? get() = _transactions.value


    fun onSearchChange(value: String) {
        _uiState.update{currentState ->
            currentState.copy(
                currentSearch = value
            )
        }
        getTransactions()
    }

    fun switchTransaction(
        isSpending: Boolean
    ) {
        _uiState.update {currentState ->
            currentState.copy(
                isSpending = isSpending,
                currentSearch = ""
            )
        }
        getTransactions()
    }

    init{
        getTransactions()
        _uiState.value = CompositionUiState()
    }

    fun getCategoryIcon(
        //TODO: substituir por uma função que inclua os ícones não-default. RESPOSTA: Nao
        categoryName: String
    ): ImageVector {
        val categoryIconMap = mapOf(
            "Alimentação" to Icons.Filled.Restaurant,
            "Saúde" to Icons.Filled.HealthAndSafety,
            "Educação" to Icons.Filled.School,
            "Meta" to Icons.Filled.Flag,
            "Transporte" to Icons.Filled.DirectionsCar,
            "Exercício" to Icons.Filled.FitnessCenter,
            "Presentes" to Icons.Filled.Redeem,
            "Mercado" to Icons.Filled.ShoppingCart,
            "Casa" to Icons.Filled.House,
            "Outros" to Icons.Filled.QuestionMark,
            "Salário" to Icons.Filled.Payments,
            "Investimentos" to Icons.Filled.AccountBalance
        )
        return categoryIconMap[categoryName]?: Icons.Filled.Error
    }

    private fun getTransactions() {
        println("here")

        viewModelScope.launch {
            println("here1")

            val url = if (_uiState.value.isSpending) {
                "http://172.17.0.1:9000/finance/ganhos"
            } else {
                "http://172.17.0.1:9000/finance/gastos"
            }
            println("her3")

            withContext(Dispatchers.IO) {
                Fuel.get(url).response { _, _, result ->
                    when (result) {
                        is Result.Failure -> {
                            val error = result.getException()
                            println("Error: ${error.message}")
                        }

                        is Result.Success -> {
                            val data = String(result.get())
                            println(data)
                            val gson = Gson()
                            val itemType = object : TypeToken<List<Transaction>>() {}.type
                            val transactions: List<Transaction> = gson.fromJson(data, itemType)
                            _transactions.postValue(transactions)
                        }
                    }
                }
            }
        }
    }
}