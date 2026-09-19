package com.example.gavin
class Enemy(val type: ElementType) {
    val name: String = "${type.displayName}mon"
    val maxHp: Int = MAX_HP
    var hp: Int = MAX_HP

    fun isAlive(): Boolean = hp > 0

    fun takeDamage(amount: Int) {
        hp -= amount
        if (hp < 0) hp = 0
    }

    companion object {
        const val MAX_HP = 30
        const val ATTACK_DAMAGE = 10
    }
}
