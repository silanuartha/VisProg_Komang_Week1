package com.example.gavin

class Battle(private val wizard: Wizard, private val input: InputHandling) {

    fun start(): Boolean {
        val enemy = Enemy(ElementType.random())

        while (wizard.isAlive() && enemy.isAlive()) {
            printBattleScreen(enemy)
            when (input.readChoice("Pilih", listOf('a', 'b', 'c', 'd', 'e'))) {
                'a' -> attack(enemy, ElementType.FIRE)
                'b' -> attack(enemy, ElementType.WATER)
                'c' -> attack(enemy, ElementType.GRASS)
                'd' -> drinkPotionInBattle(enemy)
                'e' -> {
                    println("kamu kabur!")
                    return true
                }
            }

            if (!enemy.isAlive()) {
                println("\nkamu mengalahkan ${enemy.name}")
                val wasEvolved = wizard.evolved
                wizard.registerKill()
                if (!wasEvolved && wizard.evolved) {
                    println("${wizard.name} bertambah kuat")
                }
                return true
            }

            if (!wizard.isAlive()) {
                println("\nkamu dikalahkan oleh ${enemy.name}...")
                wizard.reset()
                return false
            }
        }
        return true
    }

    private fun printBattleScreen(enemy: Enemy) {
        println("\n=== BATTLE ===")
        println(wizard.name)
        println("HP: ${wizard.hp}/ ${wizard.maxHp}")
        println("Mana: ${wizard.mana}/ ${wizard.maxMana}")
        println("HP Potions: ${wizard.healthPotions}")
        println("MP Potions: ${wizard.manaPotions}")
        println()
        println(enemy.name)
        println("HP: ${enemy.hp}/ ${enemy.maxHp}")
        println("Type: ${enemy.type.displayName}")
        println("==========")
        println("a. Fire Attack")
        println("b. Water Attack")
        println("c. Grass Attack")
        println("d. Drink potion")
        println("e. Run")
    }

    private fun attack(enemy: Enemy, attackType: ElementType) {
        if (!wizard.canCastSpell()) {
            println("mana tidak cukup")
            return
        }
        wizard.useManaForSpell()

        var damage = wizard.attackDamage
        if (attackType.beats(enemy.type)) {
            damage *= 2
        }
        enemy.takeDamage(damage)
        println("${wizard.name} memberikan $damage damage ke ${enemy.name}")
        wizard.applyLifesteal()

        enemyRetaliate(enemy)
    }

    private fun drinkPotionInBattle(enemy: Enemy) {
        val choice = input.readChoice("health/mana", listOf('h', 'm'))
        val success = if (choice == 'h') wizard.drinkHealthPotion() else wizard.drinkManaPotion()

        if (!success) {
            println("tidak ada potion")
            return
        }
        println("${wizard.name} meminum potion")
        enemyRetaliate(enemy)
    }

    private fun enemyRetaliate(enemy: Enemy) {
        if (!enemy.isAlive()) return
        wizard.takeDamage(Enemy.ATTACK_DAMAGE)
        println("${enemy.name} memberikan ${Enemy.ATTACK_DAMAGE} damage ke ${wizard.name}")
    }
}
