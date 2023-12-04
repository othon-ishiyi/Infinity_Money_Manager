package com.example.infinitymoneymanager.databaseClasses

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

class DatabaseManager{
    companion object {
        private var connection: Connection? = null
        @JvmStatic
        fun openConnection() {
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/infinity",
                "root",
                "infinity"
            )
            println("Connection with database opened successfully.")
        }
        @JvmStatic
        fun closeConnection() {
            try{
                connection!!.close()
                println("Connection with database closed successfully.")
            }
            catch (e : Exception){
                println("Connection with database is already closed.")
            }
        }
        @JvmStatic
        fun insert(databaseObject: DatabaseObject){
            val name = databaseObject.getObjectName()
            val sqlColumns = databaseObject.getSqlColumnsNames()
            val sqlTable = databaseObject.getSqlTableName()
            val numberOfColumns = sqlColumns.split(",").size
            val sqlQuestionMarks = "?,".repeat(numberOfColumns - 1) + "?"

            val statement = "INSERT INTO $sqlTable ($sqlColumns) VALUES ($sqlQuestionMarks)"
            try {
                val query = connection!!.prepareStatement(statement)
                databaseObject.setQueryVariables(query)
                query.execute()
                println("$name successfully inserted.")
            }
            catch (e: Exception){
                println("Query failed. Verify the connection with database.")
            }
        }
        @JvmStatic
        fun delete(databaseObject: DatabaseObject, whereCondition: String = ""){
            val name = databaseObject.getObjectName()
            val sqlTable = databaseObject.getSqlTableName()
            var statement = "DELETE FROM $sqlTable"
            if(whereCondition != ""){
                statement += " WHERE $whereCondition"
            }
            try{
                val query = connection!!.prepareStatement(statement)
                query.execute()
                println("$name successfully deleted.")
            }
            catch (e : Exception){
                println("Query failed. Verify the connection with database.")
            }
        }
        @JvmStatic
        fun select(databaseObject: DatabaseObject, columns: String = "*",
                   whereCondition: String = "", distinctStatement: Boolean = false):
                MutableList<MutableMap<String, Any>>?{
            val name = databaseObject.getObjectName()
            val sqlTable = databaseObject.getSqlTableName()
            val distinctStr = if(distinctStatement) "DISTINCT" else ""
            var statement = "SELECT $distinctStr $columns FROM $sqlTable"
            if(whereCondition != ""){
                statement += " WHERE $whereCondition"
            }
            try {
                val query = connection!!.prepareStatement(statement)
                query.execute()
                println("Selection successfully done in $name")
                return queryToSelectResult(query)
            }
            catch (e : Exception){
                println("Query failed. Verify the connection with database.")
            }
            return null
        }
        @JvmStatic
        fun queryToSelectResult(query: PreparedStatement): MutableList<MutableMap<String, Any>>{
            fun getColumnValue(rs: ResultSet, columnIndex: Int, columnType: Int): Any {
                return when (columnType) {
                    java.sql.Types.INTEGER, java.sql.Types.SMALLINT, java.sql.Types.TINYINT -> rs.getInt(columnIndex)
                    java.sql.Types.BIGINT -> rs.getLong(columnIndex)
                    java.sql.Types.FLOAT, java.sql.Types.REAL -> rs.getFloat(columnIndex)
                    java.sql.Types.DOUBLE -> rs.getDouble(columnIndex)
                    java.sql.Types.DECIMAL, java.sql.Types.NUMERIC -> rs.getBigDecimal(columnIndex)
                    java.sql.Types.BOOLEAN -> rs.getBoolean(columnIndex)
                    else -> rs.getString(columnIndex)
                }
            }

            val resultSet = query.resultSet
            val selectResult = mutableListOf<MutableMap<String, Any>>()
            while (resultSet.next()) {
                val metaData = resultSet.metaData
                val mutableMap = mutableMapOf<String, Any>()
                for (i in 1..metaData.columnCount) {
                    val columnName = metaData.getColumnName(i)
                    val columnValue = getColumnValue(resultSet, i, metaData.getColumnType(i))
                    mutableMap[columnName] = columnValue
                }
                selectResult.add(mutableMap)
            }
            return selectResult
        }
    }
}