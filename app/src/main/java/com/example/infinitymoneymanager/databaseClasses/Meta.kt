package com.example.infinitymoneymanager

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
}