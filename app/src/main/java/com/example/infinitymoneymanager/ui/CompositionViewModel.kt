package com.example.infinitymoneymanager.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
                    category = "Alimentação",
                    description = "Rodoserv",
                    value = 20.00,
                    date = "30/11/2023",
                    periodicity = "Único",
                ),
                Transaction(
                    id = "6",
                    category = "Alimentação",
                    description = "Cantina",
                    value = 7.00,
                    date = "30/11/2023",
                    periodicity = "Único",
                ),
                Transaction(
                    id = "7",
                    category = "Alimentação",
                    description = "Moska",
                    value = 22.00,
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
                    periodicity = "Mensal"
                )
            )
            _transactions.value = transactions
        }
        else _transactions.value = null
    }
}