package com.example.infinitymoneymanager.databaseClasses

import java.sql.Date

fun main(){
    val test = DatabaseTests()
    test.selectTest()
}
class DatabaseTests {
    fun insertTest(){
        val connection = DatabaseManager.createConnection()

        val meta = Meta(
            11,
            "Teste", 2.00,
            1.00,
            Date(11000)
        )
        DatabaseManager.insert(meta, connection)

        connection.close()
    }

    fun deleteTest(){
        val connection = DatabaseManager.createConnection()

        DatabaseManager.delete(Meta(), "id < 10 AND id > 5", connection)

        connection.close()
    }

    fun selectTest(){
        val connection = DatabaseManager.createConnection()

        val result1 = DatabaseManager.select(Meta(), "id > 0", connection)

        val result2 = DatabaseManager.select(GanhoVariavel(), "fonte = 'Trabalho'", connection)

        println(result2)
    }
}