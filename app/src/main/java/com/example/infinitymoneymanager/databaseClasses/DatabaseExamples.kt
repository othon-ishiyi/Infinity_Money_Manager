package com.example.infinitymoneymanager.databaseClasses

import java.sql.Date

fun main(){
    val test = DatabaseExamples()
    test.selectExample()
}
class DatabaseExamples {
    fun insertExample(){
        DatabaseManager.openConnection()

        val meta = Meta(
            20,
            "Teste", 2.00,
            1.00,

            "10/09/2023",
        )
        DatabaseManager.insert(meta)

        DatabaseManager.closeConnection()
    }

    fun selectExample(){
        DatabaseManager.openConnection()

        val result = DatabaseManager.select(GastoFixo(), columns = "id, valor",
            whereCondition = "id > 4 AND id < 10", distinctStatement = true)

        println(result)

        DatabaseManager.closeConnection()
    }
}