package com.example.infinitymoneymanager.databaseClasses

import com.example.infinitymoneymanager.DatabaseManager
import java.sql.Date

fun main(){
    val connection = DatabaseManager.createConnection()

    val meta = Meta(
        7,
        "Teste", 2.00,
        1.00,
        Date(10000)
    )
    meta.insertIntoDatabase(connection)

    val ganhoFixo = GanhoFixo(
        7,
        "Mensal",
        1.0, "Trabalho",
        "teste",
        Date(1000000)
    )
    ganhoFixo.insertIntoDatabase(connection)

    val ganhoVariavel = GanhoVariavel(
        7,
        1.0,
        "Trabalho",
        "teste",
        Date(1000000)
    )
    ganhoVariavel.insertIntoDatabase(connection)

    val gastoFixo = GastoFixo(
        7,
        "Mensal",
        1.0,
        "Carro",
        "teste",
        Date(1000000),
        3
    )
    gastoFixo.insertIntoDatabase(connection)

    val gastoVariavel = GastoVariavel(
        7,
        1.0,
        "Carro",
        "teste",
        Date(1000000),
        2
    )
    gastoVariavel.insertIntoDatabase(connection)
}