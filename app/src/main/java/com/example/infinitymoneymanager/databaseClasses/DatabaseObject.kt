package com.example.infinitymoneymanager.databaseClasses

import java.sql.Connection

interface DatabaseObject {
    fun insertIntoDatabase(connection: Connection)

    fun deleteFromDatabase(connection: Connection, whereCondition: String)
}