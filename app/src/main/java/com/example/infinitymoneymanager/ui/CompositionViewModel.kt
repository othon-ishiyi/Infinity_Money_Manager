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
import androidx.lifecycle.ViewModel
import com.example.infinitymoneymanager.databaseClasses.revenues
import com.example.infinitymoneymanager.databaseClasses.spendings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
        //TODO: substituir por uma função que inclua os ícones não-default
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

    private fun getTransactions(
        /*TODO: substituir pela função integrada com o back-end*/
        // Se quiser pode apagar o arquivo transactions.kt que criei só pra fazer testes
    ){
        if(_uiState.value.isSpending) {
            _transactions.value = spendings
        }
        else {
            _transactions.value = revenues
        }
    }
}