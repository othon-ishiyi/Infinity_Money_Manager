package com.example.infinitymoneymanager.databaseClasses

import java.sql.Date

fun main(){
    val connection = DatabaseManager.createConnection()

    val meta = Meta(
        10,
        "Teste", 2.00,
        1.00,
        Date(10000)
    )
    DatabaseManager.insert(meta, connection)

    val ganhoFixo = GanhoFixo(
        10,
        "Mensal",
        1.0, "Trabalho",
        "teste",
        Date(1000000)
    )
    DatabaseManager.insert(ganhoFixo, connection)

    val ganhoVariavel = GanhoVariavel(
        10,
        1.0,
        "Trabalho",
        "teste",
        Date(1000000)
    )
    DatabaseManager.insert(ganhoVariavel, connection)

    val gastoFixo = GastoFixo(
        10,
        "Mensal",
        1.0,
        "Carro",
        "teste",
        Date(1000000),
        3
    )
    DatabaseManager.insert(gastoFixo, connection)

    val gastoVariavel = GastoVariavel(
        10,
        1.0,
        "Carro",
        "teste",
        Date(1000000),
        2
    )
    DatabaseManager.insert(gastoVariavel, connection)

    connection.close()
}