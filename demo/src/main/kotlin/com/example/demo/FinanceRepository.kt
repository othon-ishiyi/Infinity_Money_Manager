package com.example.demo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GastoFixoRepository : JpaRepository<GastoFixo, Int>

@Repository
interface MetaRepository : JpaRepository<Meta, Int>

@Repository
interface GastoVariavelRepository : JpaRepository<GastoVariavel, Int>

@Repository
interface GanhoFixoRepository : JpaRepository<GanhoFixo, Int>

@Repository
interface GanhoVariavelRepository : JpaRepository<GanhoVariavel, Int>