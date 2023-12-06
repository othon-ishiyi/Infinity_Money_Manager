package com.example.demo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class HelloWorldController {
    @GetMapping("/")
    fun helloWorld(): String {
        return "Hello, World!"
    }
}

@RestController
@RequestMapping("/finance")
class FinanceController(private val financeService: FinanceService) {

    @PostMapping("/gastos")
    fun createGasto(@RequestBody gastoDto: GastoDto): GastoFixo {
        println(gastoDto.categoria)
        return financeService.createGasto(gastoDto)
    }

    @PostMapping("/metas")
    fun createMeta(@RequestBody metaDto: MetaDto): Meta {
        return financeService.createMeta(metaDto)
    }

    @PostMapping("/gastos-variaveis")
    fun createGastoVariavel(@RequestBody dto: GastoVariavelDto): GastoVariavel {
        return financeService.createGastoVariavel(dto)
    }

    @PostMapping("/ganhos-fixos")
    fun createGanhoFixo(@RequestBody dto: GanhoFixoDto): GanhoFixo {
        return financeService.createGanhoFixo(dto)
    }

    @PostMapping("/ganhos-variaveis")
    fun createGanhoVariavel(@RequestBody dto: GanhoVariavelDto): GanhoVariavel {
        return financeService.createGanhoVariavel(dto)
    }

    @GetMapping("/gastos")
    fun getAllGastos(): ResponseEntity<List<Any>> {
        val gastos = financeService.getAllGastos()
        return ResponseEntity.ok(gastos)
    }

    @GetMapping("/ganhos")
    fun getAllGanhos(): ResponseEntity<List<Any>> {
        val ganhos = financeService.getAllGanhos()
        return ResponseEntity.ok(ganhos)
    }

    @GetMapping("/metas")
    fun getAllMetasConv(): ResponseEntity<List<MetaConv>> {
        val metasConv = financeService.getAllMetasConv()
        return ResponseEntity.ok(metasConv)
    }


}

data class GastoDto(val periodicidade: String, val valor: Double, val categoria: String, val descricao: String?, val data: String, val metasId: Int?)
data class MetaDto(val nome: String, val valorAlvo: Double, val valorArrecadado: Double, val prazo: String)
data class GastoVariavelDto(val valor: Double, val categoria: String, val descricao: String?, val data: String?, val metasId: Int?)
data class GanhoFixoDto(val periodicidade: String, val valor: Double, val categoria: String, val descricao: String?, val data: String?)
data class GanhoVariavelDto(val valor: Double, val categoria: String, val descricao: String?, val data: String?)