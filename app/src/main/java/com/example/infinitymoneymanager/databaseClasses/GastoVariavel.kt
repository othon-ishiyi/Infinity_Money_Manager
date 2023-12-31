package com.example.infinitymoneymanager.databaseClasses

import java.sql.Date
import java.sql.PreparedStatement

class GastoVariavel(
    private var id: Int = 0,
    private var valor: Double = 0.0,
    private var categoria: String = "Outro",
    private var descricao: String = "",
    private var data: Date = Date(0),
    private var metasId: Int = 0
) : DatabaseObject() {
    override val name: String
        get() = "Gasto Variável"
    override val sqlTable: String
        get() = "gastos_variaveis"
    override val sqlColumns: String
        get() = "id, valor, categoria, descricao, data, metas_id"

    fun getId(): Int {return id}
    fun getValor(): Double {return valor}
    fun getCategoria(): String {return categoria}
    fun getDescricao(): String {return descricao}
    fun getData(): Date {return data}
    fun getMetasId(): Int {return metasId}

    override fun setQueryVariables(query: PreparedStatement) {
        query.setInt(1, this.getId())
        query.setDouble(2, this.getValor())
        query.setString(3, this.getCategoria())
        query.setString(4, this.getDescricao())
        query.setDate(5, this.getData())
        query.setInt(6, this.getMetasId())
    }
}