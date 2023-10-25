package com.example.infinitymoneymanager.databaseClasses

import java.sql.Connection
import java.sql.Date

class GanhoVariavel(
    private var id: Int,
    private var valor: Double,
    private var fonte: String,
    private var descricao: String,
    private var data: Date
) {
    fun getId(): Int {return id}
    fun getValor(): Double {return valor}
    fun getFonte(): String {return fonte}
    fun getDescricao(): String {return descricao}
    fun getData(): Date {return data}

    fun insertIntoDatabase(connection: Connection){
        val insertGanhoVariavelSql = "INSERT INTO ganhos_variaveis" +
                "(id, valor, fonte, descricao, data)" +
                "VALUES (?, ?, ?, ?, ?)"
        val queryInsertGanhoVariavel = connection.prepareStatement(insertGanhoVariavelSql)
        queryInsertGanhoVariavel.setInt(1, this.getId())
        queryInsertGanhoVariavel.setDouble(2, this.getValor())
        queryInsertGanhoVariavel.setString(3, this.getFonte())
        queryInsertGanhoVariavel.setString(4, this.getDescricao())
        queryInsertGanhoVariavel.setDate(5, this.getData())
        queryInsertGanhoVariavel.execute()

        println("Ganho Variável successfully inserted.")
    }
}