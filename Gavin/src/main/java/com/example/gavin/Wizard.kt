package com.example.gavin
class Wizard(var name: String) {
    var hp: Int = BASE_MAX_HP
    var mana: Int = BASE_MAX_MANA
    var healthPotions: Int = 5
    var manaPotions: Int = 5
    var kills: Int = 0
    var evolved: Boolean = false
    var lifesteal: Int = 0

    val maxHp: Int
        get() = if (evolved) (BASE_MAX_HP * 1.5).toInt() else BASE_MAX_HP

    val maxMana: Int
        get() = if (evolved) (BASE_MAX_MANA * 1.5).toInt() else BASE_MAX_MANA

    val attackDamage: Int
        get() = if (evolved) (BASE_ATTACK_DAMAGE * 1.5).toInt() else BASE_ATTACK_DAMAGE

    fun isAlive(): Boolean = hp > 0

    fun canCastSpell(): Boolean = mana >= SPELL_MANA_COST

    fun useManaForSpell() {
        mana -= SPELL_MANA_COST
    }

    fun takeDamage(amount: Int) {
        hp -= amount
        if (hp < 0) hp = 0
    }

    fun drinkHealthPotion(): Boolean {
        if (healthPotions <= 0) return false
        healthPotions--
        hp = minOf(hp + HP_POTION_HEAL, maxHp)
        return true
    }

    fun drinkManaPotion(): Boolean {
        if (manaPotions <= 0) return false
        manaPotions--
        mana = minOf(mana + MP_POTION_HEAL, maxMana)
        return true
    }

    fun applyLifesteal() {
        if (evolved && lifesteal > 0) {
            hp = minOf(hp + lifesteal, maxHp)
        }
    }

    fun registerKill() {
        kills++
        if (evolved) {
            lifesteal++
        } else if (kills >= KILLS_TO_EVOLVE) {
            evolve()
        }
    }

    private fun evolve() {
        evolved = true
        hp = maxHp
        mana = maxMana
        lifesteal = 1
    }

    fun reset() {
        hp = BASE_MAX_HP
        mana = BASE_MAX_MANA
        healthPotions = 5
        manaPotions = 5
        kills = 0
        evolved = false
        lifesteal = 0
    }

    companion object {
        const val BASE_MAX_HP = 50
        const val BASE_MAX_MANA = 30
        const val KILLS_TO_EVOLVE = 5
        const val BASE_ATTACK_DAMAGE = 10
        const val SPELL_MANA_COST = 10
        const val HP_POTION_HEAL = 25
        const val MP_POTION_HEAL = 15
    }
}
