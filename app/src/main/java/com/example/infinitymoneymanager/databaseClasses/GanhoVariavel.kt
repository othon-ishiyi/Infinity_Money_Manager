package com.example.infinitymoneymanager.databaseClasses

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
}