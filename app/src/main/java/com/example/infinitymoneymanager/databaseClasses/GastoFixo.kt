package com.example.infinitymoneymanager.databaseClasses

import java.sql.Connection
import java.sql.Date

class GastoFixo(
    private var id: Int,
    private var periodicidade: String,
    private var valor: Double,
    private var categoria: String,
    private var descricao: String,
    private var data: Date,
    private var metasId: Int
) : DatabaseObject {
    fun getId(): Int {return id}
    fun getPeriodicidade(): String {return periodicidade}
    fun getValor(): Double {return valor}
    fun getCategoria(): String {return categoria}
    fun getDescricao(): String {return descricao}
    fun getData(): Date {return data}
    fun getMetasId(): Int {return metasId}

    override fun insertIntoDatabase(connection: Connection){
        val insertGastoFixoSql = "INSERT INTO gastos_fixos" +
                "(id, periodicidade, valor, categoria, descricao, data, metas_id)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)"
        val queryInsertGastoFixo = connection.prepareStatement(insertGastoFixoSql)
        queryInsertGastoFixo.setInt(1, this.getId())
        queryInsertGastoFixo.setString(2, this.getPeriodicidade())
        queryInsertGastoFixo.setDouble(3, this.getValor())
        queryInsertGastoFixo.setString(4, this.getCategoria())
        queryInsertGastoFixo.setString(5, this.getDescricao())
        queryInsertGastoFixo.setDate(6, this.getData())
        queryInsertGastoFixo.setInt(7, this.getMetasId())
        queryInsertGastoFixo.execute()

        println("Gasto Fixo successfully inserted.")
    }

    override fun deleteFromDatabase(connection: Connection, whereCondition: String) {
        TODO("Not yet implemented")
    }
}