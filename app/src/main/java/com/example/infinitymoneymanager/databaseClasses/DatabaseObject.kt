package com.example.infinitymoneymanager.databaseClasses

import java.sql.Connection
import java.sql.PreparedStatement

abstract class DatabaseObject{
    abstract val name: String
    abstract val sqlTable: String
    abstract val sqlColumns: String

    abstract fun setQueryVariables(query: PreparedStatement)

    fun insertIntoDatabase(connection: Connection){
        val numberOfColumns = sqlColumns.split(",").size
        val sqlInterrogations = "(" + "?,".repeat(numberOfColumns - 1) + "?)"
        val statement = "INSERT INTO $sqlTable $sqlColumns VALUES $sqlInterrogations"
        val query = connection.prepareStatement(statement)
        setQueryVariables(query)
        query.execute()
        println("$name successfully inserted.")
    }

    fun deleteFromDatabase(connection: Connection, whereCondition: String){
        val statement = "DELETE FROM $sqlTable WHERE $whereCondition"
        val query = connection.prepareStatement(statement)
        query.execute()
        println("$name successfully deleted.")
    }
}