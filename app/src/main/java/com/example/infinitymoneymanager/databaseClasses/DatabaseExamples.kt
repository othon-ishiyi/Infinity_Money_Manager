package com.example.infinitymoneymanager.databaseClasses

import java.sql.Date

fun main(){
    val test = DatabaseExamples()
    test.selectExample()
}
class DatabaseExamples {
    fun insertExample(){
        val connection = DatabaseManager.createConnection()

        val meta = Meta(
            20,
            "Teste", 2.00,
            1.00,
            Date(11000)
        )
        DatabaseManager.insert(meta, connection)

        connection.close()
    }

    fun deleteExample(){
        val connection = DatabaseManager.createConnection()

        DatabaseManager.delete(Meta(), connection, "WHERE id > 15 AND id < 0")

        connection.close()
    }

    fun selectExample(){
        val connection = DatabaseManager.createConnection()

        val result1 = DatabaseManager.select(Meta(), connection)

        val result2 = DatabaseManager.select(GanhoVariavel(), connection)

        val result3 = DatabaseManager.select(GastoFixo(), connection, columns = "valor", distinctStatement = true)

        println(result3)
    }
}