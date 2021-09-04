package com.vmmarinc.sweetanikca2.data.mock

import com.vmmarinc.sweetanikca2.data.models.Product

class ProductMock {
    fun loadProducts(): List<Product>{
        return listOf(
            Product(1, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 1", "$5000",  "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(2, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 2", "$10000", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(3, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 3", "$20000", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(4, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 1", "$5000",  "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(5, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 2", "$10000", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(6, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 3", "$20000", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(7, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 1", "$5000",  "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(8, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 2", "$10000", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(9, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 3", "$20000", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(10, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 4", "$30000", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit")
        )
    }
}