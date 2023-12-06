package com.example.demo

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.Date


data class MetaConv(
    var id: Int = 0,
    var nome: String = "",
    var valorAlvo: Double = 0.0,
    var valorArrecadado: Double = 0.0,
    var prazo: Date = Date(0)
)

data class Transaction(
    val id: String,
    val category: String,
    val description: String?,
    val value: Double,
    val date: String,
    val periodicity: String?
)

@Entity
@Table(name = "gastos_fixos")
class GastoFixo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val periodicidade: String,
    val valor: BigDecimal,
    val categoria: String,
    val descricao: String?,
    val data: Date?,
    @ManyToOne @JoinColumn(name = "metas_id")
    val meta: Meta?
)

@Entity
@Table(name = "metas")
class Meta(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val nome: String,
    val valorAlvo: BigDecimal,
    val valorArrecadado: BigDecimal,
    val prazo: Date?
)

@Entity
@Table(name = "gastos_variaveis")
class GastoVariavel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val valor: BigDecimal,
    val categoria: String,
    val descricao: String?,
    val data: Date?,
    @ManyToOne @JoinColumn(name = "metas_id")
    val meta: Meta?
)

@Entity
@Table(name = "ganhos_fixos")
class GanhoFixo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val periodicidade: String,
    val valor: BigDecimal,
    val fonte: String,
    val descricao: String?,
    val data: Date?
)

@Entity
@Table(name = "ganhos_variaveis")
class GanhoVariavel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val valor: BigDecimal,
    val fonte: String,
    val descricao: String?,
    val data: Date?
)