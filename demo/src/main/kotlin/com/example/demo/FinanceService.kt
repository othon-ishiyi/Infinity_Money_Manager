package com.example.demo

import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Date

@Service
class FinanceService(
    private val gastoFixoRepository: GastoFixoRepository,
    private val metaRepository: MetaRepository,
    private val gastoVariavelRepository: GastoVariavelRepository,
    private val ganhoFixoRepository: GanhoFixoRepository,
    private val ganhoVariavelRepository: GanhoVariavelRepository
) {
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    private val outputDateFormat = SimpleDateFormat("dd/MM/yyyy")

    fun createGasto(gastoDto: GastoDto): GastoFixo {
        println(gastoDto.categoria)
        val gasto = GastoFixo(
            periodicidade = gastoDto.periodicidade,
            valor = BigDecimal(gastoDto.valor),
            categoria = gastoDto.categoria,
            descricao = gastoDto.descricao,
            data = dateFormat.parse(gastoDto.data),
            meta = gastoDto.metasId?.let { metaRepository.findById(it).orElse(null) }
        )
        return gastoFixoRepository.save(gasto)
    }

    fun createMeta(metaDto: MetaDto): Meta {
        val meta = Meta(
            nome = metaDto.nome,
            valorAlvo = BigDecimal(metaDto.valorAlvo),
            valorArrecadado = BigDecimal(metaDto.valorArrecadado),
            prazo = dateFormat.parse(metaDto.prazo)
        )
        return metaRepository.save(meta)
    }

    fun createGastoVariavel(dto: GastoVariavelDto): GastoVariavel {
        val gastoVariavel = GastoVariavel(
            valor = BigDecimal(dto.valor),
            categoria = dto.categoria,
            descricao = dto.descricao,
            data = dto.data?.let { dateFormat.parse(it) },
            meta = dto.metasId?.let { metaRepository.findById(it).orElse(null) }
        )
        return gastoVariavelRepository.save(gastoVariavel)
    }

    fun createGanhoFixo(dto: GanhoFixoDto): GanhoFixo {
        val ganhoFixo = GanhoFixo(
            periodicidade = dto.periodicidade,
            valor = BigDecimal(dto.valor),
            fonte = dto.categoria,
            descricao = dto.descricao,
            data = dto.data?.let { dateFormat.parse(it) }
        )
        return ganhoFixoRepository.save(ganhoFixo)
    }

    fun createGanhoVariavel(dto: GanhoVariavelDto): GanhoVariavel {
        val ganhoVariavel = GanhoVariavel(
            valor = BigDecimal(dto.valor),
            fonte = dto.categoria,
            descricao = dto.descricao,
            data = dto.data?.let { dateFormat.parse(it) }
        )
        return ganhoVariavelRepository.save(ganhoVariavel)
    }

    fun getAllGastos(): List<Transaction> {
        val gastosFixos = gastoFixoRepository.findAll().map { convertToTransaction(it) }
        val gastosVariaveis = gastoVariavelRepository.findAll().map { convertToTransaction(it) }
        return gastosFixos + gastosVariaveis
    }

    fun getAllGanhos(): List<Transaction> {
        val ganhosFixos = ganhoFixoRepository.findAll().map { convertToTransaction(it) }
        val ganhosVariaveis = ganhoVariavelRepository.findAll().map { convertToTransaction(it) }
        return ganhosFixos + ganhosVariaveis
    }

    private fun convertToTransaction(gasto: GastoFixo): Transaction {
        return Transaction(
            id = gasto.id.toString(),
            category = gasto.categoria,
            description = gasto.descricao,
            value = gasto.valor.toDouble(),
            date = gasto.data?.let { outputDateFormat.format(it) } ?: "Unknown",
            periodicity = gasto.periodicidade
        )
    }

    private fun convertToTransaction(gasto: GastoVariavel): Transaction {
        return Transaction(
            id = gasto.id.toString(),
            category = gasto.categoria,
            description = gasto.descricao,
            value = gasto.valor.toDouble(),
            date = gasto.data?.let { outputDateFormat.format(it) } ?: "Unknown",
            periodicity = "Único"
        )
    }

    private fun convertToTransaction(ganho: GanhoFixo): Transaction {
        return Transaction(
            id = ganho.id.toString(),
            category = ganho.fonte,
            description = ganho.descricao,
            value = ganho.valor.toDouble(),
            date = ganho.data?.let { outputDateFormat.format(it) } ?: "Unknown",
            periodicity = ganho.periodicidade
        )
    }

    private fun convertToTransaction(ganho: GanhoVariavel): Transaction {
        return Transaction(
            id = ganho.id.toString(),
            category = ganho.fonte,
            description = ganho.descricao,
            value = ganho.valor.toDouble(),
            date = ganho.data?.let { outputDateFormat.format(it) } ?: "Unknown",
            periodicity = "Único"
        )
    }
    fun getAllMetasConv(): List<MetaConv> {
        return metaRepository.findAll().map { convertToMetaConv(it) }
    }

    private fun convertToMetaConv(meta: Meta): MetaConv {
        return MetaConv(
            id = meta.id ?: 0,
            nome = meta.nome,
            valorAlvo = meta.valorAlvo.toDouble(),
            valorArrecadado = meta.valorArrecadado.toDouble(),
            prazo = meta.prazo ?: Date(0)
        )
    }

}
