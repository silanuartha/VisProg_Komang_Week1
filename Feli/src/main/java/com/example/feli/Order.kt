package com.example.feli
data class Order(
    val customerName: String,
    val items: List<OrderItem>
) {
    val total: Double
        get() = items.sumOf { it.subtotal }
}
