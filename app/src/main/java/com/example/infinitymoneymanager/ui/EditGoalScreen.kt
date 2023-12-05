package com.example.infinitymoneymanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType.Companion.Number

import com.example.infinitymoneymanager.ui.goals // TODO: remover após integração com o backend
import java.text.SimpleDateFormat
import java.util.Locale

private fun validateData(nome: String, valorAlvo: String, prazo: String): Boolean {
    val regexPattern = "[\\t ]*((\\d{1,3}?)+(,\\d{1,2})?)"

    return nome.isNotEmpty()
            && valorAlvo.matches(Regex(regexPattern))
            && prazo.isNotEmpty()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditGoalScreen(id: Int) {
    val goal = goals.getOrNull(id)

    var nome by remember { mutableStateOf(goal?.getNome().orEmpty()) }
    var valorAlvo by remember { mutableStateOf(goal?.getValorAlvo()?.toString()?.replace('.', ',') ?: "") }
    var prazo by remember { mutableStateOf(goal?.getPrazo()?.let { date ->
        SimpleDateFormat("dd/MM/yyyy", Locale("pt-br")).format(date)
    } ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = valorAlvo,
            onValueChange = { valorAlvo = it },
            label = { Text("Valor Alvo") },
            keyboardOptions = KeyboardOptions(
                keyboardType = Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        val focusManager = LocalFocusManager.current
        var showDatePickerDialog by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis > System.currentTimeMillis()
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
                                    prazo = millis.toBrazilianDateFormat()
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
            value = prazo,
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
                Text(text = "Prazo")
            },
            readOnly = true
        )

        Button(
            onClick = {
                if (validateData(nome, valorAlvo, prazo)) {
                    // TODO: editar a meta
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Editar Meta")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun EditGoalScreenPreview() {
    EditGoalScreen(id = 1)
}
