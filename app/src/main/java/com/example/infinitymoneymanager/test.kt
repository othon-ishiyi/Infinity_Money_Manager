package com.example.infinitymoneymanager
import java.sql.Date

import com.example.infinitymoneymanager.databaseClasses.GanhoFixo
import com.example.infinitymoneymanager.databaseClasses.GanhoVariavel
import com.example.infinitymoneymanager.databaseClasses.GastoFixo
import com.example.infinitymoneymanager.databaseClasses.GastoVariavel

fun main(){
    val dbManager = DatabaseManager()
    val connection = dbManager.createConnection()

    val gastoFixo = GastoFixo(1, "Mensal", 1.0, "Carro", "teste", Date(1000000), 1)
    dbManager.insertGastoFixo(connection, gastoFixo)

    val gastoVariavel = GastoVariavel(1, 1.0, "Carro", "teste", Date(1000000), 1)
    dbManager.insertGastoVariavel(connection, gastoVariavel)

    /*
    val meta = Meta(4, "Teste", 2.00, 1.00, Date(10000))
    dbManager.insertMeta(connection, meta)

    val ganhoFixo = GanhoFixo(2, "Mensal", 1.0, "Trabalho", "teste", Date(1000000))
    dbManager.insertGanhoFixo(connection, ganhoFixo)
    val ganhoVariavel = GanhoVariavel(1,1.0, "Trabalho", "teste", Date(1000000))
    dbManager.insertGanhoVariavel(connection, ganhoVariavel)
    */
}