package com.example.infinitymoneymanager

import com.example.infinitymoneymanager.databaseClasses.GanhoFixo
import com.example.infinitymoneymanager.databaseClasses.GanhoVariavel
import com.example.infinitymoneymanager.databaseClasses.GastoFixo
import com.example.infinitymoneymanager.databaseClasses.GastoVariavel
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

    fun insertGanhoFixo(connection: Connection, ganhoFixo: GanhoFixo){
        val insertGanhoFixoSql = "INSERT INTO ganhos_fixos" +
                "(id, periodicidade, valor, fonte, descricao, data)" +
                "VALUES (?, ?, ?, ?, ?, ?)"
        val queryInsertGanhoFixo = connection.prepareStatement(insertGanhoFixoSql)
        queryInsertGanhoFixo.setInt(1, ganhoFixo.getId())
        queryInsertGanhoFixo.setString(2, ganhoFixo.getPeriodicidade())
        queryInsertGanhoFixo.setDouble(3, ganhoFixo.getValor())
        queryInsertGanhoFixo.setString(4, ganhoFixo.getFonte())
        queryInsertGanhoFixo.setString(5, ganhoFixo.getDescricao())
        queryInsertGanhoFixo.setDate(6, ganhoFixo.getData())
        queryInsertGanhoFixo.execute()

        println("Ganho Fixo successfully inserted.")
    }

    fun insertGanhoVariavel(connection: Connection, ganhoVariavel: GanhoVariavel){
        val insertGanhoVariavelSql = "INSERT INTO ganhos_variaveis" +
                "(id, valor, fonte, descricao, data)" +
                "VALUES (?, ?, ?, ?, ?)"
        val queryInsertGanhoVariavel = connection.prepareStatement(insertGanhoVariavelSql)
        queryInsertGanhoVariavel.setInt(1, ganhoVariavel.getId())
        queryInsertGanhoVariavel.setDouble(2, ganhoVariavel.getValor())
        queryInsertGanhoVariavel.setString(3, ganhoVariavel.getFonte())
        queryInsertGanhoVariavel.setString(4, ganhoVariavel.getDescricao())
        queryInsertGanhoVariavel.setDate(5, ganhoVariavel.getData())
        queryInsertGanhoVariavel.execute()

        println("Ganho Variável successfully inserted.")
    }

    fun insertGastoFixo(connection: Connection, gastoFixo: GastoFixo){
        val insertGastoFixoSql = "INSERT INTO gastos_fixos" +
                "(id, periodicidade, valor, categoria, descricao, data, metas_id)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)"
        val queryInsertGastoFixo = connection.prepareStatement(insertGastoFixoSql)
        queryInsertGastoFixo.setInt(1, gastoFixo.getId())
        queryInsertGastoFixo.setString(2, gastoFixo.getPeriodicidade())
        queryInsertGastoFixo.setDouble(3, gastoFixo.getValor())
        queryInsertGastoFixo.setString(4, gastoFixo.getCategoria())
        queryInsertGastoFixo.setString(5, gastoFixo.getDescricao())
        queryInsertGastoFixo.setDate(6, gastoFixo.getData())
        queryInsertGastoFixo.setInt(7, gastoFixo.getMetasId())
        queryInsertGastoFixo.execute()

        println("Gasto Fixo successfully inserted.")
    }

    fun insertGastoVariavel(connection: Connection, gastoVariavel: GastoVariavel){
        val insertGastoVariavelSql = "INSERT INTO gastos_variaveis" +
                "(id, valor, categoria, descricao, data, metas_id)" +
                "VALUES (?, ?, ?, ?, ?, ?)"
        val queryInsertGastoVariavel = connection.prepareStatement(insertGastoVariavelSql)
        queryInsertGastoVariavel.setInt(1, gastoVariavel.getId())
        queryInsertGastoVariavel.setDouble(2, gastoVariavel.getValor())
        queryInsertGastoVariavel.setString(3, gastoVariavel.getCategoria())
        queryInsertGastoVariavel.setString(4, gastoVariavel.getDescricao())
        queryInsertGastoVariavel.setDate(5, gastoVariavel.getData())
        queryInsertGastoVariavel.setInt(6, gastoVariavel.getMetasId())
        queryInsertGastoVariavel.execute()

        println("Gasto Variável successfully inserted.")
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