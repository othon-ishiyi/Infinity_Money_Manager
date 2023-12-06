package com.example.infinitymoneymanager.ui

import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.SelectableDates
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.infinitymoneymanager.R
import com.example.infinitymoneymanager.ui.components.CategoryGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    navController: NavController,
    compositionViewModel: CompositionViewModel,
    addTransactionViewModel: AddTransactionViewModel = viewModel(),
) {
    val compositionUiState by compositionViewModel.uiState.collectAsState()
    val isSpending = compositionUiState.isSpending
    addTransactionViewModel.getTransactionCategories(spending = isSpending)
    val addTransactionUiState by addTransactionViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
        TextField(
            value = addTransactionViewModel.transactionValue,
            onValueChange = {addTransactionViewModel.setTransaction(it)},
            label = {Text(text = "Valor")},
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .fillMaxWidth()
        )

        val focusManager = LocalFocusManager.current
        var showDatePickerDialog by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= System.currentTimeMillis()
            }
        })

        if (showDatePickerDialog) {
            DatePickerDialog(
                onDismissRequest = { showDatePickerDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            datePickerState
                                .selectedDateMillis?.let { millis ->
                                    addTransactionViewModel.editDate(millis.toBrazilianDateFormat())
                                }
                            showDatePickerDialog = false
                        }) {
                        Text(text = "OK")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
        TextField(
            value = addTransactionViewModel.date,
            onValueChange = { },
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .onFocusEvent {
                    if (it.isFocused) {
                        showDatePickerDialog = true
                        focusManager.clearFocus(force = true)
                    }
                },
            label = {
                Text("Data")
            },
            readOnly = true
        )

        Text(
            text = "Categoria",
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(dimensionResource(id = R.dimen.padding_small))
        )

        CategoryGrid(
            categories = addTransactionViewModel.categories,
            onClick = addTransactionViewModel::chooseCategory,
            selectedCategoryName = (addTransactionUiState.categoryName)
        )

        Text(
            text = "Recorrência",
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(dimensionResource(id = R.dimen.padding_small))
        )

        RecurrenceRow(
            onClick = addTransactionViewModel::chooseRecurrence,
            selectedRecurrenceName = (addTransactionUiState.recurrenceName),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Descrição",
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(dimensionResource(id = R.dimen.padding_small))
        )

        TextField(
            value = addTransactionViewModel.description,
            onValueChange = {addTransactionViewModel.setDescriptionString(it)},
            label = {Text(text = "Descrição")},
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_small))
        )

        Spacer(modifier = Modifier.weight(1.0f))

        Button(
            onClick = {
                addTransactionViewModel.clickAddTransaction(
                    navController = navController,
                    route = "composition"
                )

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_small))
                .height(48.dp)
        ) {
            Text(
                text = if (!addTransactionUiState.errorState) "Adicionar"
                else "Faltam dados"
            )
        }
    }
}


@Composable()
fun RecurrenceRow(
    onClick: (Recurrence) -> Unit,
    selectedRecurrenceName: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ){
        recurrenceOptions.forEach{ recurrence ->
            OutlinedButton(
                onClick = {onClick(recurrence)},
                colors = if(selectedRecurrenceName == recurrence.name) {
                    ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                } else {
                    ButtonDefaults.outlinedButtonColors()
                },
                modifier = modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .width(80.dp)
            ) {
                Text(
                    text = recurrence.name,
                    fontSize = 8.sp
                )
            }
        }
    }
}
