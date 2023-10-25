package com.example.infinitymoneymanager.databaseClasses

import java.sql.Connection
import java.sql.Date

class GastoVariavel(
    private var id: Int,
    private var valor: Double,
    private var categoria: String,
    private var descricao: String,
    private var data: Date,
    private var metasId: Int
): DatabaseObject {
    fun getId(): Int {return id}
    fun getValor(): Double {return valor}
    fun getCategoria(): String {return categoria}
    fun getDescricao(): String {return descricao}
    fun getData(): Date {return data}
    fun getMetasId(): Int {return metasId}

    override fun insertIntoDatabase(connection: Connection){
        val insertGastoVariavelSql = "INSERT INTO gastos_variaveis" +
                "(id, valor, categoria, descricao, data, metas_id)" +
                "VALUES (?, ?, ?, ?, ?, ?)"
        val queryInsertGastoVariavel = connection.prepareStatement(insertGastoVariavelSql)
        queryInsertGastoVariavel.setInt(1, this.getId())
        queryInsertGastoVariavel.setDouble(2, this.getValor())
        queryInsertGastoVariavel.setString(3, this.getCategoria())
        queryInsertGastoVariavel.setString(4, this.getDescricao())
        queryInsertGastoVariavel.setDate(5, this.getData())
        queryInsertGastoVariavel.setInt(6, this.getMetasId())
        queryInsertGastoVariavel.execute()

        println("Gasto Variável successfully inserted.")
    }

    override fun deleteFromDatabase(connection: Connection, whereCondition: String) {
        TODO("Not yet implemented")
    }
}