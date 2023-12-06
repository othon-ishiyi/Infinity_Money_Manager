package com.example.infinitymoneymanager.databaseClasses

import com.example.infinitymoneymanager.ui.Transaction

var spendings = mutableListOf<Transaction>(
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
        category = "Exercício",
        description = "Whey Protein",
        value = 80.00,
        date = "30/11/2023",
        periodicity = "Único",
    ),
    Transaction(
        id = "6",
        category = "Casa",
        description = "Conserto do Chuveiro",
        value = 20.00,
        date = "30/11/2023",
        periodicity = "Único",
    ),
    Transaction(
        id = "7",
        category = "Presentes",
        description = "Presente pro Sonar",
        value = 15.00,
        date = "28/11/2023",
        periodicity = "Único",
    )
)

val revenues = mutableListOf<Transaction>(
    Transaction(
        id = "1",
        category = "Salário",
        description = "NFT",
        value = 20000.00,
        date = "Hoje 02/12/2023",
        periodicity = "Mensal",
    ),
    Transaction(
        id = "2",
        category = "Presentes",
        description = "Aniversário",
        value = 350.00,
        date = "Hoje 02/12/2023",
        periodicity = "Único",
    ),
    Transaction(
        id = "3",
        category = "Outros",
        description = "Bet",
        value = 50.30,
        date = "Ontem 01/12/2023",
        periodicity = "Único",
    )
)