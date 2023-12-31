package com.example.infinitymoneymanager.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.infinitymoneymanager.R
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompositionScreen(
    compositionViewModel: CompositionViewModel = viewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val compositionUiState by compositionViewModel.uiState.collectAsState()

    Column() {
        Row(

        ) {
            TextButton(
                onClick = {compositionViewModel.switchTransaction(true)},
            ) {
                Text(
                    text = stringResource(id = R.string.spendings),
                    color = if (compositionUiState.isSpending) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondary
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            TextButton(
                onClick = {compositionViewModel.switchTransaction(false)},
            ) {
                Text(
                    text = stringResource(id = R.string.revenues),
                    color = if (!compositionUiState.isSpending) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondary
                )
            }
        }
        Row(

        ) {
            OutlinedTextField(
                value = compositionUiState.currentSearch,
                onValueChange = {compositionViewModel.onSearchChange(value = it)},
                label = { Text("Pesquisar") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .width(200.dp)
            )
            Spacer(modifier = Modifier.weight(1.0f))
            OutlinedButton(
                onClick = { navController.navigate("filter_screen") },
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Filled.FilterAlt,
                    contentDescription = "Search"
                )
                Text(
                    text = "Filtro"
                )
            }
        }
        Divider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)

        AllTransactions(
            transactions = compositionViewModel.transactions,
            compositionViewModel = compositionViewModel,
            modifier = Modifier
        )
    }
}

@Composable
fun AllTransactions(
    transactions: List<Transaction>?,
    compositionViewModel: CompositionViewModel,
    modifier: Modifier = Modifier
) {
    if(transactions == null) {
        return
    }
    // Returns a column of DayTransactions object
    val groupedTransactions = transactions.groupBy { it.date }

    LazyColumn(modifier = modifier) {
        groupedTransactions.forEach { (date, transactionList) ->
            item{
                DayTransactions(
                    date = date,
                    transactions = transactionList,
                    compositionViewModel = compositionViewModel,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}

@Composable
fun DayTransactions(
    date: String,
    transactions: List<Transaction>,
    compositionViewModel: CompositionViewModel,
    modifier: Modifier = Modifier
) {
    // Returns a column of TransactionInfoCard objects
    val totalValue = transactions.sumOf { it.value }
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row {
            Text(
                text = date,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                text = String.format("%.2f", totalValue),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
        }
        Divider(color = MaterialTheme.colorScheme.tertiaryContainer, thickness = 1.dp)

        Column {
            transactions.forEach { transaction ->
                TransactionInfoCard(
                    transaction = transaction,
                    icon = compositionViewModel.getCategoryIcon(transaction.category),
                    modifier = Modifier
                )
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionInfoCard(
    transaction: Transaction,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    // Returns a button with the informations about the transaction
    Card(
        onClick = {/*TODO: deve abrir uma janela para editar o gasto/ganho*/},
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ){
            Icon(
                imageVector = icon,
                contentDescription = transaction.category,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            ){
                Text(
                    text = transaction.category,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = transaction.description,
                    fontSize = 8.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                text = String.format("%.2f", transaction.value),
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

//Plus button in the bottom right corner



@Preview(showBackground = true)
@Composable
fun AllTransactionsPreview() {
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
            id = "2",
            category = "Meta",
            description = "Fusquinha",
            value = 850.00,
            date = "Ontem 01/12/2023",
            periodicity = "Único",
        ),
        Transaction(
            id = "2",
            category = "Educação",
            description = "Ichiban",
            value = 350.00,
            date = "Ontem 01/12/2023",
            periodicity = "Mensal",
        ),
        Transaction(
            id = "2",
            category = "Alimentação",
            description = "Rodoserv",
            value = 20.00,
            date = "30/11/2023",
            periodicity = "Único",
        )
    )
    CompositionScreen(
        navController = rememberNavController()
    )
}