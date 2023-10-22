package com.example.infinitymoneymanager

import java.sql.Date
class Meta(private var id: Int,
           private var nome: String,
           private var valorAlvo: Float,
           private var valorArrecadado: Float,
           private var prazo: Date
) {
    fun getId(): Int {return id}
    fun getNome(): String {return nome}
    fun getValorAlvo(): Float {return valorAlvo}
    fun getValorArrecadado(): Float {return valorArrecadado}
    fun getPrazo(): Date {return prazo}
}