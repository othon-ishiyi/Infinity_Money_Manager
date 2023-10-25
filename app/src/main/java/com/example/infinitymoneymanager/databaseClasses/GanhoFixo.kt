package com.example.infinitymoneymanager.databaseClasses

import java.sql.Date
import java.sql.Connection

class GanhoFixo(
    private var id: Int,
    private var periodicidade: String,
    private var valor: Double,
    private var fonte: String,
    private var descricao: String,
    private var data: Date
) {
    fun getId(): Int {return id}
    fun getPeriodicidade(): String {return periodicidade}
    fun getValor(): Double {return valor}
    fun getFonte(): String {return fonte}
    fun getDescricao(): String {return descricao}
    fun getData(): Date {return data}

    fun insertIntoDatabase(connection: Connection){
        val insertGanhoFixoSql = "INSERT INTO ganhos_fixos" +
                "(id, periodicidade, valor, fonte, descricao, data)" +
                "VALUES (?, ?, ?, ?, ?, ?)"
        val queryInsertGanhoFixo = connection.prepareStatement(insertGanhoFixoSql)
        queryInsertGanhoFixo.setInt(1, this.getId())
        queryInsertGanhoFixo.setString(2, this.getPeriodicidade())
        queryInsertGanhoFixo.setDouble(3, this.getValor())
        queryInsertGanhoFixo.setString(4, this.getFonte())
        queryInsertGanhoFixo.setString(5, this.getDescricao())
        queryInsertGanhoFixo.setDate(6, this.getData())
        queryInsertGanhoFixo.execute()

        println("Ganho Fixo successfully inserted.")
    }
}