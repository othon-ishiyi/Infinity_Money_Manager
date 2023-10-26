package com.example.infinitymoneymanager.databaseClasses

fun main(){
    val connection = DatabaseManager.createConnection()

    val result1 = DatabaseManager.select(Meta(), "id > 0", connection)

    val result2 = DatabaseManager.select(GanhoVariavel(), "fonte = 'Trabalho'", connection)

    println(result2)
}