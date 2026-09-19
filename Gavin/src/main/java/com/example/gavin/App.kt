package com.example.gavin
class App {
    private val input = InputHandling()
    private lateinit var wizard: Wizard

    fun run() {
        val name = input.readText("What's your name?")
        wizard = Wizard(name)
        println("\nGood luck, ${wizard.name}! You're gonna need it!\n")

        while (true) {
            println("What're you going to do?")
            println("1. View Stats")
            println("2. Enter battle")
            when (input.readIntInRange("Pilih", 1, 2)) {
                1 -> viewStats()
                2 -> Battle(wizard, input).start()
            }
            println()
        }
    }

    private fun viewStats() {
        var viewing = true
        while (viewing) {
            printStatsScreen()
            when (input.readChoice("pilih", listOf('a', 'b', 'c', 'd'))) {
                'a' -> {
                    if (wizard.drinkManaPotion()) {
                        println("${wizard.name} meminum mana potion")
                    } else {
                        println("tidak ada mana potion")
                    }
                }
                'b' -> {
                    if (wizard.drinkHealthPotion()) {
                        println("${wizard.name} meminum health potion")
                    } else {
                        println("tidak ada health potion")
                    }
                }
                'c' -> {
                    val newName = input.readText("masukkan nama baru")
                    wizard.name = newName
                    println("Namamu sekarang ${wizard.name}")
                }
                'd' -> viewing = false
            }
            println()
        }
    }

    private fun printStatsScreen() {
        println("=== ${wizard.name}'s STATS ===")
        println("HP: ${wizard.hp}/ ${wizard.maxHp}")
        println("Mana: ${wizard.mana}/ ${wizard.maxMana}")
        println("Kills needed to evolve: ${wizard.kills}/ ${Wizard.KILLS_TO_EVOLVE}")
        println("Mana Potions held: ${wizard.manaPotions}")
        println("Health Potions held: ${wizard.healthPotions}")
        println("=================")
        println("a. Drink Mana Potion")
        println("b. Drink Health Potion")
        println("c. Rename self")
        println("d. Back")
    }
}
