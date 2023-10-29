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

        DatabaseManager.delete(Meta(), connection, "id > 5 AND id < 12")

        connection.close()
    }

    fun selectExample(){
        val connection = DatabaseManager.createConnection()

        val result = DatabaseManager.select(GastoFixo(), connection, columns = "id, valor",
            whereCondition = "id > 4 AND id < 10", distinctStatement = true)

        println(result)
    }
}