package com.example.gavin
enum class ElementType {
    FIRE, WATER, GRASS;
    fun beats(other: ElementType): Boolean {
        return when (this) {
            FIRE -> other == GRASS
            WATER -> other == FIRE
            GRASS -> other == WATER
        }
    }
    val displayName: String
        get() = name.lowercase().replaceFirstChar { it.uppercase() }
    companion object {
        fun random(): ElementType = values().random()
    }
}
