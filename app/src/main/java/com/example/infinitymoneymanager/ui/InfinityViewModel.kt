package com.example.infinitymoneymanager.ui

/*TODO: remover quando a classe Transaction estiver integrada ao ViewModel*/
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
        category = "Alimentação",
        description = "Rodoserv",
        value = 20.00,
        date = "30/11/2023",
        periodicity = "Único",
    ),
    Transaction(
        id = "6",
        category = "Alimentação",
        description = "Cantina",
        value = 7.00,
        date = "30/11/2023",
        periodicity = "Único",
    ),
    Transaction(
        id = "7",
        category = "Alimentação",
        description = "Moska",
        value = 22.00,
        date = "28/11/2023",
        periodicity = "Único",
    )
)

class InfinityViewModel {
}