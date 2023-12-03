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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompositionScreen(
    transactions: List<Transaction>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column() {
        Row(

        ) {
            TextButton(
                onClick = {/*TODO: alterar o estado correspondente à visual. de Despesas ou Receitas*/ },
            ) {
                Text(
                    text = stringResource(id = R.string.spendings)
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            TextButton(
                onClick = {/*TODO: alterar o estado correspondente à visual. de Despesas ou Receitas*/ },
            ) {
                Text(
                    text = stringResource(id = R.string.revenues)
                )
            }
        }
        Row(

        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {/*TODO: Alterar estado correspondente a value*/ },
                label = { Text("Pesquisar") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                },
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
            transactions = transactions,
            modifier = Modifier
        )
    }
}

@Composable
fun AllTransactions(
    transactions: List<Transaction>,
    modifier: Modifier = Modifier
) {
    // Returns a column of DayTransactions object
    val groupedTransactions = transactions.groupBy { it.date }

    LazyColumn(modifier = modifier) {
        groupedTransactions.forEach { (date, transactionList) ->
            item{
                DayTransactions(
                    date = date,
                    transactions = transactionList,
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
                    modifier = Modifier
                )
            }
        }

    }
}

private fun getCategoryIcon(category: String): ImageVector{
    /*TODO ajustar conforme os nomes de categorias utilizados no Banco de Dados*/
    val categoryIconMap = mapOf(
        "Alimentação" to Icons.Filled.Restaurant,
        "Saúde" to Icons.Filled.HealthAndSafety,
        "Educação" to Icons.Filled.School,
        "Meta" to Icons.Filled.Flag
    )
     return categoryIconMap[category]?: Icons.Filled.Error
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionInfoCard(
    transaction: Transaction,
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
                imageVector = getCategoryIcon(transaction.category),
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
        transactions = transactions,
        navController = rememberNavController()
    )
}