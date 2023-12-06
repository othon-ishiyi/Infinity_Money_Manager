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
    ){
        if(_uiState.value.isSpending) {
            val transactions = listOf(
                Transaction(
                    id = "1",
                    category = "Alimentação",
                    description = "Churraskilo",
                    value = 22.96,
                    date = "Hoje 02/12/2023",
                    periodicity = "Único",
                ),
                Transaction(
                    id = "2",
                    category = "Saúde",
                    description = "Psicólogo",
                    value = 70.00,
                    date = "Hoje 02/12/2023",
                    periodicity = "Único",
                ),
                Transaction(
                    id = "3",
                    category = "Meta",
                    description = "Fusquinha",
                    value = 850.00,
                    date = "Ontem 01/12/2023",
                    periodicity = "Único",
                ),
                Transaction(
                    id = "4",
                    category = "Educação",
                    description = "Ichiban",
                    value = 350.00,
                    date = "Ontem 01/12/2023",
                    periodicity = "Mensal",
                ),
                Transaction(
                    id = "5",
                    category = "Exercício",
                    description = "Whey Protein",
                    value = 80.00,
                    date = "30/11/2023",
                    periodicity = "Único",
                ),
                Transaction(
                    id = "6",
                    category = "Casa",
                    description = "Conserto do Chuveiro",
                    value = 20.00,
                    date = "30/11/2023",
                    periodicity = "Único",
                ),
                Transaction(
                    id = "7",
                    category = "Presentes",
                    description = "Presente pro Sonar",
                    value = 15.00,
                    date = "28/11/2023",
                    periodicity = "Único",
                )
            )
            _transactions.value = transactions
        }
        else if (uiState.value.currentSearch != ""){
            val transactions = listOf(
                Transaction(
                    id = "1",
                    category = "Salário",
                    description = "NFT",
                    value = 20000.00,
                    date = "Hoje 02/12/2023",
                    periodicity = "Mensal",
                ),
                Transaction(
                    id = "2",
                    category = "Presentes",
                    description = "Aniversário",
                    value = 350.00,
                    date = "Hoje 02/12/2023",
                    periodicity = "Único",
                ),
                Transaction(
                    id = "3",
                    category = "Outros",
                    description = "Bet",
                    value = 50.30,
                    date = "Ontem 01/12/2023",
                    periodicity = "Único",
                )
            )
            _transactions.value = transactions
        }
        else _transactions.value = null
    }
}