package br.com.silas.orgs.models

import java.math.BigDecimal

data class Product(
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val imagem: String? = null,
)

