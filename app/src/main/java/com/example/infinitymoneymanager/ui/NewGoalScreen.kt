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
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}

private fun validateData(nome: String, valorAlvo: String, valorArrecadado: String, prazo: String): Boolean {
    val regexPattern = "[\\t ]*((\\d{1,3}?)+(,\\d{1,2})?)"

    return nome.isNotEmpty()
            && valorAlvo.matches(Regex(regexPattern))
            && prazo.isNotEmpty()
            && (valorArrecadado.isEmpty() || valorArrecadado.matches(Regex(regexPattern)))
}


data class TransactionPost(
    val nome: String,
    val valorAlvo: String,
    val valorArrecadado: String,
    val prazo: String
)

fun postTransaction(transactionData: TransactionPost, url: String) = runBlocking {
    val json = Gson().toJson(transactionData)

    Fuel.post(url)
        .header(Headers.CONTENT_TYPE, "application/json")
        .body(json, Charset.forName("UTF-8")) // Explicitly specify the charset
        .response { _, response, result ->
            when (result) {
                is Result.Failure -> {
                    val error = result.getException()
                    println("Error: ${error.message}")
                }
                is Result.Success -> {
                    println("Posted successfully: ${String(response.data)}")
                }
            }
        }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewGoalScreen() {
    var nome by remember { mutableStateOf("") }
    var valorAlvo by remember { mutableStateOf("") }
    var valorArrecadado by remember { mutableStateOf("") }
    var prazo by remember { mutableStateOf("") }

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

        OutlinedTextField(
            value = valorArrecadado,
            onValueChange = { valorArrecadado = it },
            label = { Text("Valor Arrecadado") },
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
                Text("Prazo")
            },
            readOnly = true
        )

        Button(
            onClick = {
                if (validateData(nome, valorAlvo, valorArrecadado,  prazo)) {
                    postTransaction(TransactionPost(
                        nome = nome,
                        valorAlvo = valorAlvo,
                        valorArrecadado = valorArrecadado,
                        prazo = prazo
                    ), "http://172.17.0.1:9000/finance/metas")
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Criar Nova Meta")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewGoalScreenPreview() {
    NewGoalScreen()
}
