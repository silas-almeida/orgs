package br.com.silas.orgs.models

import java.math.BigDecimal

data class Product(
    val name: String,
    val description: String,
    val value: BigDecimal,
    val imageUrl: String? = null,
)

