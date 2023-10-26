package com.example.infinitymoneymanager.databaseClasses

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

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
            val sqlQuestionMarks = "?,".repeat(numberOfColumns - 1) + "?"

            val statement = "INSERT INTO $sqlTable ($sqlColumns) VALUES ($sqlQuestionMarks)"
            val query = connection.prepareStatement(statement)
            databaseObject.setQueryVariables(query)
            query.execute()
            println("$name successfully inserted.")
        }
        @JvmStatic
        fun delete(databaseObject: DatabaseObject, whereCondition: String, connection: Connection){
            val name = databaseObject.getObjectName()
            val sqlTable = databaseObject.getSqlTableName()
            val statement = "DELETE FROM $sqlTable $whereCondition"

            val query = connection.prepareStatement(statement)
            query.execute()
            println("$name successfully deleted.")
        }

        @JvmStatic
        fun select(databaseObject: DatabaseObject, whereCondition: String,
                   connection: Connection): MutableList<MutableMap<String, Any>>{
            val name = databaseObject.getObjectName()
            val sqlTable = databaseObject.getSqlTableName()

            val statement = "SELECT * FROM $sqlTable $whereCondition"
            val query = connection.prepareStatement(statement)
            query.execute()
            println("Selection successfully done in $name")

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