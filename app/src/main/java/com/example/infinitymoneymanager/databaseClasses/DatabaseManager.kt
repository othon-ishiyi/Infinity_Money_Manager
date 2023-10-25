package com.example.infinitymoneymanager.databaseClasses

import java.sql.Connection
import java.sql.DriverManager

class DatabaseManager{
    companion object {
        @JvmStatic
        fun createConnection(): Connection {
            val connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/infinity",
                "root",
                "infinity"
            )
            println("Connection with database created with success.")
            return connection
        }
    }
}