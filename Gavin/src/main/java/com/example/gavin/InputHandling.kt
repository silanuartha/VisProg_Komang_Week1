package com.example.gavin
import java.util.Scanner

class InputHandling(private val scanner: Scanner = Scanner(System.`in`)) {

    fun readText(prompt: String): String {
        while (true) {
            print("$prompt: ")
            val text = scanner.nextLine().trim()
            if (text.isNotEmpty()) return text
            println("Tidak boleh kosong")
        }
    }

    fun readChoice(prompt: String, allowed: List<Char>): Char {
        while (true) {
            print("$prompt (${allowed.joinToString("/")}): ")
            val text = scanner.nextLine().trim().lowercase()
            if (text.length == 1 && allowed.contains(text[0])) {
                return text[0]
            }
            println("Pilih salah satu: ${allowed.joinToString(", ")}")
        }
    }

    fun readIntInRange(prompt: String, min: Int, max: Int): Int {
        while (true) {
            print("$prompt ($min-$max): ")
            val text = scanner.nextLine().trim()
            val value = text.toIntOrNull()
            when {
                value == null -> println("Masukkan angka")
                value < min || value > max -> println("harus diantara $min dan $max.")
                else -> return value
            }
        }
    }
}
