package com.example.infinitymoneymanager.databaseClasses

import java.sql.Date
import java.sql.PreparedStatement

class Meta(
    private var id: Int = 0,
    private var nome: String = "",
    private var valorAlvo: Double = 0.0,
    private var valorArrecadado: Double = 0.0,
    private var prazo: Date = Date(0)
): DatabaseObject() {
    override val name: String
        get() = "Meta"
    override val sqlTable: String
        get() = "metas"
    override val sqlColumns: String
        get() = "id, nome, valor_alvo, valor_arrecadado, prazo"

    fun getId(): Int {return id}
    fun getNome(): String {return nome}
    fun getValorAlvo(): Double {return valorAlvo}
    fun getValorArrecadado(): Double {return valorArrecadado}
    fun getPrazo(): Date {return prazo}

    override fun setQueryVariables(query: PreparedStatement) {
        query.setInt(1, this.getId())
        query.setString(2, this.getNome())
        query.setDouble(3, this.getValorAlvo())
        query.setDouble(4, this.getValorArrecadado())
        query.setDate(5, this.getPrazo())
    }
}