package com.example.feli
class App {
    private val input = InputHandling()
    private val menu = MenuList()
    private val orders = OrderList()

    fun run() {
        var running = true
        while (running) {
            printMainMenu()
            when (input.readIntInRange("pilih", 1, 7)) {
                1 -> makeOrder()
                2 -> viewOrders()
                3 -> viewMenu()
                4 -> addMenu()
                5 -> editMenu()
                6 -> deleteMenu()
                7 -> {
                    println("goodbye")
                    running = false
                }
            }
            println()
        }
    }

    private fun printMainMenu() {
        println("========== ORDER SYSTEM ==========")
        println("1. Make Order")
        println("2. View Orders")
        println("3. View Menu")
        println("4. Add Menu")
        println("5. Edit Menu")
        println("6. Delete Menu")
        println("7. Exit")
        println("===================================")
    }

    private fun viewMenu() {
        println("\n========= MENU =========")
        if (menu.isEmpty()) {
            println("Menu kosong")
        } else {
            val items = menu.getAll()
            for (i in items.indices) {
                val item = items[i]
                println("${i + 1}. ${item.name} - ${item.description} - ${formatPrice(item.price)}")
            }
        }
        println("========================")
    }

    private fun addMenu() {
        val name = input.readText("Nama makanan/minuman")
        val description = input.readText("Deskripsi")
        val price = input.readDouble("Harga")
        menu.add(MenuItem(name, description, price))
        println("Menu ditambahkan")
    }

    private fun editMenu() {
        if (menu.isEmpty()) {
            println("Menu kosong")
            return
        }
        viewMenu()
        val number = input.readInt("pilih menu untuk di edit")
        if (number == 0) {
            println("batal")
            return
        }
        if (menu.getByNumber(number) == null) {
            println("menu tidak ditemukan")
            return
        }
        val name = input.readText("Nama baru")
        val description = input.readText("Deskripsi baru")
        val price = input.readDouble("Harga baru")
        menu.updateByNumber(number, name, description, price)
        println("Menu diperbaharui")
    }

    private fun deleteMenu() {
        if (menu.isEmpty()) {
            println("Menu kosong")
            return
        }
        viewMenu()
        val number = input.readInt("pilih menu untuk dihapus")
        if (number == 0) {
            println("Dibatalkan")
            return
        }
        if (menu.deleteByNumber(number)) {
            println("Menu dihapus")
        } else {
            println("menu tidak ditemukan")
        }
    }

    private fun makeOrder() {
        if (menu.isEmpty()) {
            println("Menu kosong")
            return
        }

        val customerName = input.readText("Nama")
        val orderItems = mutableListOf<OrderItem>()

        var adding = true
        while (adding) {
            viewMenu()
            val number = input.readInt("Masukkan nomor menu untuk order (0 selesai)")
            if (number == 0) {
                adding = false
                continue
            }
            val item = menu.getByNumber(number)
            if (item == null) {
                println("Menu tidak ditemukan")
                continue
            }
            val quantity = input.readInt("Jumlah")
            if (quantity <= 0) {
                println("Jumlah harus lebih besar dari 0")
                continue
            }
            orderItems.add(OrderItem(item, quantity))
            println("ditambahkan: ${item.name} x$quantity")
        }

        if (orderItems.isEmpty()) {
            return
        }

        val order = Order(customerName, orderItems)
        orders.add(order)
        println()
        printReceipt(order)
    }

    private fun viewOrders() {
        println("\n========= ORDERS =========")
        if (orders.isEmpty()) {
            println("Tidak ada orderan")
        } else {
            for (order in orders.getAll()) {
                printReceipt(order)
                println()
            }
        }
        println("===========================")
    }

    private fun printReceipt(order: Order) {
        println("=== ${order.customerName}'s ORDER ===")
        order.items.forEachIndexed { index, item ->
            println("${index + 1}. ${item.menuItem.name} x${item.quantity}    ${formatPrice(item.subtotal)}")
        }
        println("================")
        println("Total    ${formatPrice(order.total)}")
    }

    private fun formatPrice(price: Double): String {
        return if (price == price.toLong().toDouble()) {
            "$${price.toLong()}"
        } else {
            "$${"%.2f".format(price)}"
        }
    }
}
