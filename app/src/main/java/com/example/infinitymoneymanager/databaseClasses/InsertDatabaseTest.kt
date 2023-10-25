package com.example.infinitymoneymanager.databaseClasses

import java.sql.Date

fun main(){
    val connection = DatabaseManager.createConnection()

    val meta = Meta(
        11,
        "Teste", 2.00,
        1.00,
        Date(11000)
    )
    DatabaseManager.insert(meta, connection)

    val ganhoFixo = GanhoFixo(
        11,
        "Mensal",
        1.0, "Trabalho",
        "teste",
        Date(1100000)
    )
    DatabaseManager.insert(ganhoFixo, connection)

    val ganhoVariavel = GanhoVariavel(
        11,
        1.0,
        "Trabalho",
        "teste",
        Date(1100000)
    )
    DatabaseManager.insert(ganhoVariavel, connection)

    val gastoFixo = GastoFixo(
        11,
        "Mensal",
        1.0,
        "Carro",
        "teste",
        Date(1100000),
        3
    )
    DatabaseManager.insert(gastoFixo, connection)

    val gastoVariavel = GastoVariavel(
        11,
        1.0,
        "Carro",
        "teste",
        Date(1100000),
        2
    )
    DatabaseManager.insert(gastoVariavel, connection)

    connection.close()
}