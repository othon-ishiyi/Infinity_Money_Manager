package com.example.infinitymoneymanager
import java.lang.Exception
import java.sql.Date

fun main(){
    try{
        Class.forName("com.mysql.cj.jdbc.Driver")
        val dbManager = DatabaseManager();
        val connection = dbManager.createConnection();
    } catch (e: Exception) {
        e.printStackTrace()
        println("It was not possible to connect with the database.")
    }
}