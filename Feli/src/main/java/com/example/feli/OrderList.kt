package com.example.feli
class OrderList {
    private val orders = mutableListOf<Order>()

    fun add(order: Order) {
        orders.add(order)
    }

    fun getAll(): List<Order> = orders

    fun isEmpty(): Boolean = orders.isEmpty()
}
