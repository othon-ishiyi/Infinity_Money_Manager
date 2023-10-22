package com.example.infinitymoneymanager

import java.sql.Connection
import java.sql.DriverManager

class DatabaseManager{
    private val serverName = "localhost"
    private val databaseName = "infinity"

    fun createConnection(): Connection{
        val connection = DriverManager.getConnection(
            "jdbc:mysql://$serverName/$databaseName",
            "root",
            "infinity"
        )
        println("Connection made with success.")
        return connection
    }

    fun insertMeta(connection: Connection, meta: Meta){
        val insertMetaSql = "INSERT INTO metas " +
                "(id, nome, valor_alvo, valor_arrecadado, prazo) " +
                "VALUES (?, ?, ?, ?, ?)"

        val queryInsertMeta = connection.prepareStatement(insertMetaSql)
        queryInsertMeta.setInt(1, meta.getId())
        queryInsertMeta.setString(2, meta.getNome())
        queryInsertMeta.setDouble(3, meta.getValorAlvo())
        queryInsertMeta.setDouble(4, meta.getValorArrecadado())
        queryInsertMeta.setDate(5, meta.getPrazo())
        queryInsertMeta.execute()

        println("Meta successfully inserted.")
    }
}