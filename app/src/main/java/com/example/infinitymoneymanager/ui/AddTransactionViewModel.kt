package com.example.infinitymoneymanager.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.infinitymoneymanager.databaseClasses.revenues
import com.example.infinitymoneymanager.databaseClasses.spendings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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

data class AddTransactionUiState(
    // errorState é ativado se o usuário tentar adicionar transação faltando dados
    val errorState : Boolean = false,
    val categoryName: String = "",
    val recurrenceName: String = ""
)

class AddTransactionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AddTransactionUiState())
    val uiState: StateFlow<AddTransactionUiState> = _uiState.asStateFlow()
    var transactionValue by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set
    var date by mutableStateOf("")
        private set

    private val _categories = MutableLiveData<List<Category>>()
    val categories: List<Category>? get() = _categories.value

    private var isSpending : Boolean = true

    fun getTransactionCategories(
        spending: Boolean
    ) {
        isSpending = spending
        if (spending) {
            //TODO: substituir por função que retorne categorias de gasto padrão + metas
            _categories.value = DefaultSpendingCategories
        }
        else {
            _categories.value = DefaultRevenuesCategories
        }
    }

    fun resetViewModel() {
        transactionValue = ""
        description = ""
        date = ""
        _categories.value = emptyList<Category>()
        _uiState.value = AddTransactionUiState()
    }

    init {
        resetViewModel()
    }
    fun setTransaction(value: String) {
        transactionValue = value
    }

    fun setDescriptionString(value: String) {
        description = value
    }

    fun editDate(value: String) {
        date = value
    }

    fun chooseCategory(category: Category) {
        if (_uiState.value.categoryName == category.name){
            _uiState.update{currentState ->
                currentState.copy(
                    categoryName = ""
                )
            }
        }
        else{
            _uiState.update{currentState ->
                currentState.copy(
                    categoryName = category.name
                )
            }
        }
    }

    fun chooseRecurrence(recurrence: Recurrence) {
        if (_uiState.value.recurrenceName == recurrence.name){
            _uiState.update{currentState ->
                currentState.copy(
                    recurrenceName = ""
                )
            }
        }
        else{
            _uiState.update{currentState ->
                currentState.copy(
                    recurrenceName = recurrence.name
                )
            }
        }
    }

    fun addTransactionToDB(
        //TODO: adicionar as infos pro BD. Fiz um código simulado só pra fins de teste
        // Se quiser pode apagar o arquivo transactions.kt que criei só pra fazer o teste
        isSpending: Boolean //true se for gasto, false se for ganho
        // ...
    ) {
        if (isSpending) {
            spendings.add(
                Transaction(
                    id = "?",
                    category = uiState.value.categoryName,
                    description = description,
                    value = transactionValue.toDouble(),
                    date = date,
                    periodicity = uiState.value.recurrenceName,
                )
            )
        }
        else {
            revenues.add(
                Transaction(
                    id = "?",
                    category = uiState.value.categoryName,
                    description = description,
                    value = transactionValue.toDouble(),
                    date = date,
                    periodicity = uiState.value.recurrenceName,
                )
            )
        }
    }

    fun clickAddTransaction(
        navController: NavController,
        route : String
    ) {
        if(
            transactionValue == "" ||
            date == "" ||
            _uiState.value.recurrenceName == "" ||
            _uiState.value.categoryName == ""
        ) {
            _uiState.update{currentState ->
                currentState.copy(
                    errorState = true
                )
            }
        }
        else {
            addTransactionToDB(
                isSpending = isSpending
            )
            resetViewModel()
            navController.navigate(route)
        }
    }
}