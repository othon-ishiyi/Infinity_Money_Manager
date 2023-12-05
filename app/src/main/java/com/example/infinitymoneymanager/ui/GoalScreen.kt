package com.example.infinitymoneymanager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.infinitymoneymanager.databaseClasses.Meta
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit
import androidx.compose.material.icons.filled.Edit
import java.text.NumberFormat
import java.time.ZoneId
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

val goals = listOf(
    Meta(
        nome = "Carro novo",
        valorAlvo = 75000.0,
        valorArrecadado = 33750.0,
        prazo = java.sql.Date.valueOf(LocalDate.now().plusDays(800).toString())
    ),
    Meta(
        nome = "Troca de celular",
        valorAlvo = 4199.0,
        valorArrecadado = 3359.2,
        prazo = java.sql.Date.valueOf(LocalDate.now().plusDays(120).toString())
    ),
    Meta(
        nome = "Entrada do apartamento",
        valorAlvo = 150000.0,
        valorArrecadado = 12000.0,
        prazo = java.sql.Date.valueOf(LocalDate.now().plusDays(1500).toString())
    ),
    Meta(
        nome = "Viagem de final de ano",
        valorAlvo = 16000.0,
        valorArrecadado = 3420.0,
        prazo = java.sql.Date.valueOf(LocalDate.now().plusDays(15).toString()) // 30 days from now
    )
)
fun formatCurrency(value: Double): String {
    val numberFormat = NumberFormat.getCurrencyInstance()
    return numberFormat.format(value)
}

fun getRemainingTime(dateSql: java.sql.Date): String {
    val date = java.util.Date(dateSql.time)
    val today = LocalDate.now()
    val targetDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

    val period = Period.between(today, targetDate)
    val daysDifference = ChronoUnit.DAYS.between(today, targetDate)

    return when {
        daysDifference < 0 -> "Expirado"
        period.years == 1 -> return when (period.months) {
            0 -> "1 ano"
            1 -> "1 ano, 1 mês"
            else -> "1 ano, ${period.months} meses"
        }
        period.years > 0 -> return when (period.months) {
            0 -> "${period.years} anos"
            1 -> "${period.years} anos, 1 mês"
            else -> "${period.years} anos, ${period.months} meses"
        }
        period.months == 1 -> "1 mes"
        period.months > 0 -> "${period.months} meses"
        Math.toIntExact(daysDifference) == 1 -> "1 dia"
        daysDifference > 0 -> "$daysDifference dias"
        else -> "Hoje"
    }
}
@Composable
fun GoalScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row {
            val goalsSize = if (goals.isEmpty()) "Nenhuma meta"
            else if (goals.size == 1) "1 Meta"
            else "${goals.size} Metas"

            Text(
                text = goalsSize,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.weight(1.0f))
            NewGoalButton(navController = navController)


        }


        AllGoals(goals = goals)
    }
}

@Composable
fun GoalCard(
    goal: Meta,
    modifier: Modifier = Modifier.padding(vertical = 12.dp)
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        modifier = modifier
            .height(134.dp)
            .fillMaxWidth()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 0.dp, top = 8.dp, start = 8.dp, end = 8.dp)
        ) {
            Text(
                text = goal.getNome(),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(modifier = Modifier.weight(1.0f))
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "",
                modifier = Modifier.size(15.dp),
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }

        Divider(
            modifier = Modifier
                .padding(bottom = 6.dp, top = 6.dp, start = 8.dp, end = 8.dp)
                .background(MaterialTheme.colorScheme.onSecondaryContainer),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )


        LinearProgressIndicator(
            progress = goal.getValorArrecadado().toFloat() / goal.getValorAlvo().toFloat(),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(horizontal = 8.dp)
                .height(12.dp),
            color = MaterialTheme.colorScheme.primaryContainer // Set the color to match the background
        )


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Valor arrecadado",
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Text(
                    text = formatCurrency(goal.getValorArrecadado()),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Valor alvo",
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Text(
                    text = formatCurrency(goal.getValorAlvo()),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

        }

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "Prazo restante",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = getRemainingTime(goal.getPrazo()),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }



    }
}

@Composable
fun AllGoals(
    goals: List<Meta>,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier) {
        goals.forEach {
            item{
                GoalCard(goal = it)
            }
        }
    }
}

@Composable
fun NewGoalButton(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        modifier = modifier
            .width(130.dp)
            .height(30.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable {
                    navController.navigate("new_goal_screen") {

                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Nova meta",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GoalCardPreview() {
    GoalCard(
        goal = goals[0]
    )
}

@Preview(showBackground = true)
@Composable
fun GoalScreenPreview() {
    GoalScreen(navController = rememberNavController())
}