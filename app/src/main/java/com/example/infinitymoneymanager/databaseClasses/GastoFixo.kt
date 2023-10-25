package com.example.infinitymoneymanager.databaseClasses

import java.sql.Date
import java.sql.PreparedStatement

class GastoFixo(
    private var id: Int,
    private var periodicidade: String,
    private var valor: Double,
    private var categoria: String,
    private var descricao: String,
    private var data: Date,
    private var metasId: Int
) : DatabaseObject() {
    override val name: String
        get() = "Gasto Fixo"
    override val sqlTable: String
        get() = "gastos_fixos"
    override val sqlColumns: String
        get() = "(id, periodicidade, valor, categoria, descricao, data, metas_id)"

    fun getId(): Int {return id}
    fun getPeriodicidade(): String {return periodicidade}
    fun getValor(): Double {return valor}
    fun getCategoria(): String {return categoria}
    fun getDescricao(): String {return descricao}
    fun getData(): Date {return data}
    fun getMetasId(): Int {return metasId}

    override fun setQueryVariables(query: PreparedStatement) {
        query.setInt(1, this.getId())
        query.setString(2, this.getPeriodicidade())
        query.setDouble(3, this.getValor())
        query.setString(4, this.getCategoria())
        query.setString(5, this.getDescricao())
        query.setDate(6, this.getData())
        query.setInt(7, this.getMetasId())
    }
}