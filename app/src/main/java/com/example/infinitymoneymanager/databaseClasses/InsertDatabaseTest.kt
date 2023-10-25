package com.example.infinitymoneymanager.databaseClasses
import com.example.infinitymoneymanager.DatabaseManager
import com.example.infinitymoneymanager.Meta
import java.sql.Date

fun main(){
    val dbManager = DatabaseManager()
    val connection = dbManager.createConnection()

    val meta = Meta(5,
        "Teste", 2.00,
        1.00,
        Date(10000))
    dbManager.insertMeta(connection, meta)

    val ganhoFixo = GanhoFixo(5,
        "Mensal",
        1.0, "Trabalho",
        "teste",
        Date(1000000))
    dbManager.insertGanhoFixo(connection, ganhoFixo)

    val ganhoVariavel = GanhoVariavel(5,
        1.0,
        "Trabalho",
        "teste",
        Date(1000000))
    dbManager.insertGanhoVariavel(connection, ganhoVariavel)

    val gastoFixo = GastoFixo(5,
        "Mensal",
        1.0,
        "Carro",
        "teste",
        Date(1000000),
        3)
    dbManager.insertGastoFixo(connection, gastoFixo)

    val gastoVariavel = GastoVariavel(5,
        1.0,
        "Carro",
        "teste",
        Date(1000000),
        2)
    dbManager.insertGastoVariavel(connection, gastoVariavel)
}