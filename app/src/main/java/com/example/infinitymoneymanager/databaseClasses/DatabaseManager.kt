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
        @JvmStatic
        fun insert(databaseObject: DatabaseObject, connection: Connection){
            val name = databaseObject.getObjectName()
            val sqlColumns = databaseObject.getSqlColumnsNames()
            val sqlTable = databaseObject.getSqlTableName()
            val numberOfColumns = sqlColumns.split(",").size
            val sqlInterrogations = "(" + "?,".repeat(numberOfColumns - 1) + "?)"
            val statement = "INSERT INTO $sqlTable $sqlColumns VALUES $sqlInterrogations"
            val query = connection.prepareStatement(statement)
            databaseObject.setQueryVariables(query)
            query.execute()
            println("$name successfully inserted.")
        }
        @JvmStatic
        fun delete(databaseObject: DatabaseObject, whereCondition: String, connection: Connection){
            val name = databaseObject.getObjectName()
            val sqlTable = databaseObject.getSqlTableName()
            val statement = "DELETE FROM $sqlTable WHERE $whereCondition"
            val query = connection.prepareStatement(statement)
            query.execute()
            println("$name successfully deleted.")
        }
    }
}