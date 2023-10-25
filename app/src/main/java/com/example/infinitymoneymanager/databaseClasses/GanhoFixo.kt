package com.example.infinitymoneymanager.databaseClasses

import java.sql.Date
import java.sql.PreparedStatement

class GanhoFixo(
    private var id: Int = 0,
    private var periodicidade: String = "Mensal",
    private var valor: Double = 0.0,
    private var fonte: String = "Outro",
    private var descricao: String = "",
    private var data: Date = Date(0)
) : DatabaseObject() {
    override val name: String
        get() = "Ganho Fixo"
    override val sqlTable: String
        get() = "ganhos_fixos"
    override val sqlColumns: String
        get() = "(id, periodicidade, valor, fonte, descricao, data)"

    fun getId(): Int {return id}
    fun getPeriodicidade(): String {return periodicidade}
    fun getValor(): Double {return valor}
    fun getFonte(): String {return fonte}
    fun getDescricao(): String {return descricao}
    fun getData(): Date {return data}

    override fun setQueryVariables(query: PreparedStatement) {
        query.setInt(1, this.getId())
        query.setString(2, this.getPeriodicidade())
        query.setDouble(3, this.getValor())
        query.setString(4, this.getFonte())
        query.setString(5, this.getDescricao())
        query.setDate(6, this.getData())
    }
}