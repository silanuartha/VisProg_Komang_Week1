package com.example.feli
data class OrderItem(
    val menuItem: MenuItem,
    val quantity: Int
) {
    val subtotal: Double
        get() = menuItem.price * quantity
}
