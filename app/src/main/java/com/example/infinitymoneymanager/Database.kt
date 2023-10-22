package com.example.infinitymoneymanager

import java.sql.Connection
import java.sql.DriverManager

class DatabaseManager{
    private val serverName = "localhost"
    private val portNumber = "3306"

    fun createConnection(): Connection{
        val connection = DriverManager.getConnection(
            "jdbc:mysql://$serverName/$portNumber",
            "root",
            "infinity"
        )
        println("Connection made with success.")
        return connection
    }
}