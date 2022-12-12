package br.com.silas.orgs.dao

import br.com.silas.orgs.models.Product
import java.math.BigDecimal

class ProductsDao {

    fun add(product: Product) {
        products.add(product)
    }

    fun searchAll(): List<Product> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>(
            Product(
                nome = "Salada de frutas",
                descricao = "Laranja, uva e banana",
                valor = BigDecimal("19.99")
            )
        )
    }
}