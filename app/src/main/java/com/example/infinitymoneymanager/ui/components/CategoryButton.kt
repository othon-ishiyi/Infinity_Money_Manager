package com.example.infinitymoneymanager.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.infinitymoneymanager.ui.Category
import com.example.infinitymoneymanager.ui.DefaultSpendingCategories
import com.example.infinitymoneymanager.R

@Composable
fun CategoryButton(
    category: Category,
    onClick: () -> Unit,
    selectedCategoryName: String,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        colors = if(selectedCategoryName == category.name) {
            ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        } else {
            ButtonDefaults.outlinedButtonColors()
        },
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = category.icon,
                contentDescription = category.name,
                 modifier = Modifier
                     .size(20.dp)
            )
            Text(
                text = category.name,
                fontSize = 8.sp
            )
        }
    }
}

@Composable
fun CategoryGrid(
    categories: List<Category>?,
    onClick: (Category) -> Unit,
    selectedCategoryName: String,
    modifier : Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // 3 columns
        modifier = Modifier
    ) {
        items(categories!!.size) { index ->
            CategoryButton(
                category = categories[index],
                onClick = {onClick(categories[index])},
                selectedCategoryName = selectedCategoryName
            )
        }
    }
}

