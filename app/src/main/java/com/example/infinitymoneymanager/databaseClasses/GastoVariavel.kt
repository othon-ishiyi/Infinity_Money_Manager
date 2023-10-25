package com.example.infinitymoneymanager.databaseClasses

import java.sql.Date

class GastoVariavel(
    private var id: Int,
    private var valor: Double,
    private var categoria: String,
    private var descricao: String,
    private var data: Date,
    private var metasId: Int
    ) {
    fun getId(): Int {return id}
    fun getValor(): Double {return valor}
    fun getCategoria(): String {return categoria}
    fun getDescricao(): String {return descricao}
    fun getData(): Date {return data}
    fun getMetasId(): Int {return metasId}
}