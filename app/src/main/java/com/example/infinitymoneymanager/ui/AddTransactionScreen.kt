package com.example.infinitymoneymanager.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.infinitymoneymanager.R
import com.example.infinitymoneymanager.ui.components.CategoryGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        TextField(
            value = "",
            onValueChange = {/*TODO*/},
            label = {Text(text = "Valor")},
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
        )

        Text(
            text = "Categoria",
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(dimensionResource(id = R.dimen.padding_small))
        )

        /*TODO: Adicionar campo DATA*/

        CategoryGrid(
            DefaultSpendingCategories,
            onClick = {/*TODO*/}
        )

        Text(
            text = "Recorrência",
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(dimensionResource(id = R.dimen.padding_small))
        )

        RecurrenceRow(
            onClick = {/*TODO*/}
        )

        Text(
            text = "Descrição",
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(dimensionResource(id = R.dimen.padding_small))
        )

        TextField(
            value = "",
            onValueChange = {/*TODO*/},
            label = {Text(text = "Descrição")},
            singleLine = true,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
        )

        OutlinedButton(
            onClick = {/*TODO*/},
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .fillMaxWidth()
        ) {
            Text(
                text = "Adicionar",
                fontSize = 16.sp
            )
        }
    }
}


@Composable()
fun RecurrenceRow(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ){
        recurrenceOptions.forEach{ recurrence ->
            OutlinedButton(
                onClick = onClick,
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

@Preview(showBackground = true)
@Composable
fun AddTransactionScreenPreview() {
    AddTransactionScreen(navController = rememberNavController())
}