package com.example.infinitymoneymanager

import java.sql.Connection
import java.sql.DriverManager

class DatabaseManager{
    private val serverName = "localhost"
    private val databaseName = "infinity"

    fun createConnection(): Connection{
        val connection = DriverManager.getConnection(
            "jdbc:mysql://$serverName/$databaseName",
            "root",
            "infinity"
        )
        println("Connection made with success.")
        return connection
    }

    fun insertMeta(connection: Connection, meta: Meta){

    }
}