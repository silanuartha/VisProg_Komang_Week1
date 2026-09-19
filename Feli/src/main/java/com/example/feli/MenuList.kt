package com.example.feli
class MenuList {
    private val items = mutableListOf<MenuItem>()

    fun add(item: MenuItem) {
        items.add(item)
    }

    fun getAll(): List<MenuItem> = items

    fun getByNumber(number: Int): MenuItem? {
        val index = number - 1
        return if (index in items.indices) items[index] else null
    }

    fun updateByNumber(number: Int, name: String, description: String, price: Double): Boolean {
        val item = getByNumber(number) ?: return false
        item.name = name
        item.description = description
        item.price = price
        return true
    }

    fun deleteByNumber(number: Int): Boolean {
        val index = number - 1
        if (index !in items.indices) return false
        items.removeAt(index)
        return true
    }

    fun isEmpty(): Boolean = items.isEmpty()
}
