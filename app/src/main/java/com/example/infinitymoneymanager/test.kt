package com.example.infinitymoneymanager
import java.lang.Exception

fun main(){
    try{
        Class.forName("com.mysql.cj.jdbc.Driver")
        var dbManager = DatabaseManager();
        val connection = dbManager.createConnection();
    } catch (e: Exception) {
        e.printStackTrace()
        println("It was not possible to connect with the database.")
    }
}