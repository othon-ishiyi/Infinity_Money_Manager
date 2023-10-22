package com.example.infinitymoneymanager
import java.sql.Date

fun main(){
    val dbManager = DatabaseManager()
    val connection = dbManager.createConnection()
    val meta = Meta(1, "Teste", 2.00, 1.00, Date(10000))
    dbManager.insertMeta(connection, meta)
}