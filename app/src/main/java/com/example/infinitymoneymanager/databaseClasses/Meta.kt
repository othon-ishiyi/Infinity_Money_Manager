package com.example.infinitymoneymanager

import java.sql.Connection
import java.sql.Date

class Meta(
    private var id: Int,
    private var nome: String,
    private var valorAlvo: Double,
    private var valorArrecadado: Double,
    private var prazo: Date
) {
    fun getId(): Int {return id}
    fun getNome(): String {return nome}
    fun getValorAlvo(): Double {return valorAlvo}
    fun getValorArrecadado(): Double {return valorArrecadado}
    fun getPrazo(): Date {return prazo}

    fun insertIntoDatabase(connection: Connection){
        val insertMetaSql = "INSERT INTO metas " +
                "(id, nome, valor_alvo, valor_arrecadado, prazo) " +
                "VALUES (?, ?, ?, ?, ?)"

        val queryInsertMeta = connection.prepareStatement(insertMetaSql)
        queryInsertMeta.setInt(1, this.getId())
        queryInsertMeta.setString(2, this.getNome())
        queryInsertMeta.setDouble(3, this.getValorAlvo())
        queryInsertMeta.setDouble(4, this.getValorArrecadado())
        queryInsertMeta.setDate(5, this.getPrazo())
        queryInsertMeta.execute()

        println("Meta successfully inserted.")
    }
}