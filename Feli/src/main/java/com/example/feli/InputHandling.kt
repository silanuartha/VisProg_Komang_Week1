package com.example.feli

import java.util.Scanner
class InputHandling(private val scanner: Scanner = Scanner(System.`in`)) {

    fun readText(prompt: String): String {
        while (true) {
            print("$prompt: ")
            val text = scanner.nextLine().trim()
            if (text.isNotEmpty()) return text
            println("tidak boleh kosong")
        }
    }

    fun readDouble(prompt: String): Double {
        while (true) {
            print("$prompt: ")
            val text = scanner.nextLine().trim()
            val value = text.toDoubleOrNull()
            when {
                value == null -> println("Masukkan angka")
                value < 0 -> println("tidak boleh angka negatif")
                else -> return value
            }
        }
    }

    fun readInt(prompt: String): Int {
        while (true) {
            print("$prompt: ")
            val text = scanner.nextLine().trim()
            val value = text.toIntOrNull()
            if (value != null) return value
            println("masukkan angka")
        }
    }

    fun readIntInRange(prompt: String, min: Int, max: Int): Int {
        while (true) {
            print("$prompt ($min-$max): ")
            val text = scanner.nextLine().trim()
            val value = text.toIntOrNull()
            when {
                value == null -> println("masukkan angka")
                value < min || value > max -> println("angka harus diantara $min dan $max.")
                else -> return value
            }
        }
    }
}
