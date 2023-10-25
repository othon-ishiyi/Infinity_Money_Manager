package com.example.infinitymoneymanager.databaseClasses

import java.sql.Connection
import java.sql.PreparedStatement

abstract class DatabaseObject{
    abstract val name: String
    abstract val sqlTable: String
    abstract val sqlColumns: String

    abstract fun setQueryVariables(query: PreparedStatement)
}