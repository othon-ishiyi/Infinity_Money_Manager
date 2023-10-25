package com.example.infinitymoneymanager.databaseClasses

fun main(){
    val connection = DatabaseManager.createConnection()

    DatabaseManager.delete(Meta(), "id < 10 AND id > 5", connection)

    connection.close()
}