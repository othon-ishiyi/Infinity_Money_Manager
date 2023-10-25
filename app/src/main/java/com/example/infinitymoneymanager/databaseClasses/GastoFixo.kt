package com.example.infinitymoneymanager.databaseClasses

import java.sql.Date

class GastoFixo(
    private var id: Int,
    private var periodicidade: String,
    private var valor: Double,
    private var categoria: String,
    private var descricao: String,
    private var data: Date,
    private var metasId: Int
    ) {
    fun getId(): Int {return id}
    fun getPeriodicidade(): String {return periodicidade}
    fun getValor(): Double {return valor}
    fun getCategoria(): String {return categoria}
    fun getDescricao(): String {return descricao}
    fun getData(): Date {return data}
    fun getMetasId(): Int {return metasId}
}