package com.example.infinitymoneymanager.databaseClasses

import java.sql.Connection
import java.sql.DriverManager

class DatabaseManager{
    companion object {
        @Volatile
        private var connection: Connection? = null

        @JvmStatic
        fun getConnection(): Connection {
            if(connection == null) {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/infinity",
                    "root",
                    "infinity"
                )
            }
            println("Connection made with success.")
            return connection!!
        }
    }
}