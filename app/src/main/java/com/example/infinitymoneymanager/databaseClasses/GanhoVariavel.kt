package com.example.infinitymoneymanager.databaseClasses

import java.sql.Date
import java.sql.PreparedStatement

class GanhoVariavel(
    private var id: Int = 0,
    private var valor: Double = 0.0,
    private var fonte: String = "Outro",
    private var descricao: String = "",
    private var data: Date = Date(0)
) : DatabaseObject(){
    override val name: String
        get() = "Ganho Variável"
    override val sqlTable: String
        get() = "ganhos_variaveis"
    override val sqlColumns: String
        get() = "(id, valor, fonte, descricao, data)"

    fun getId(): Int {return id}
    fun getValor(): Double {return valor}
    fun getFonte(): String {return fonte}
    fun getDescricao(): String {return descricao}
    fun getData(): Date {return data}

    override fun setQueryVariables(query: PreparedStatement) {
        query.setInt(1, this.getId())
        query.setDouble(2, this.getValor())
        query.setString(3, this.getFonte())
        query.setString(4, this.getDescricao())
        query.setDate(5, this.getData())
    }
}