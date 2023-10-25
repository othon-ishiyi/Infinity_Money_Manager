package com.example.infinitymoneymanager

import androidx.compose.runtime.Composable
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
            println("Connection made with success.")
            return connection
        }
    }
}