package com.example.infinitymoneymanager.databaseClasses

import java.sql.Date

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
}