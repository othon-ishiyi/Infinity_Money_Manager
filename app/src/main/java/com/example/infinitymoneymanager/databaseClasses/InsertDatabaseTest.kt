package com.example.infinitymoneymanager.databaseClasses

import java.sql.Date

fun main(){
    val connection = DatabaseManager.getConnection()

    val meta = Meta(
        8,
        "Teste", 2.00,
        1.00,
        Date(10000)
    )
    meta.insertIntoDatabase(connection)

    val ganhoFixo = GanhoFixo(
        8,
        "Mensal",
        1.0, "Trabalho",
        "teste",
        Date(1000000)
    )
    ganhoFixo.insertIntoDatabase(connection)

    val ganhoVariavel = GanhoVariavel(
        8,
        1.0,
        "Trabalho",
        "teste",
        Date(1000000)
    )
    ganhoVariavel.insertIntoDatabase(connection)

    val gastoFixo = GastoFixo(
        8,
        "Mensal",
        1.0,
        "Carro",
        "teste",
        Date(1000000),
        3
    )
    gastoFixo.insertIntoDatabase(connection)

    val gastoVariavel = GastoVariavel(
        8,
        1.0,
        "Carro",
        "teste",
        Date(1000000),
        2
    )
    gastoVariavel.insertIntoDatabase(connection)
}