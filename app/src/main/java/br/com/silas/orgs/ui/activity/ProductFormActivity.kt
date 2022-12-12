package br.com.silas.orgs.ui.activity

import android.app.Activity
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import br.com.silas.orgs.R
import br.com.silas.orgs.dao.ProductsDao
import br.com.silas.orgs.databinding.ActivityProductFormBinding
import br.com.silas.orgs.databinding.ImageFormBinding
import br.com.silas.orgs.extensions.tryToLoadImage
import br.com.silas.orgs.models.Product
import br.com.silas.orgs.ui.dialog.FormImageDialog
import coil.load
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar produto"
        configureSaveButton()
        binding.activityFormImage.setOnClickListener {
            FormImageDialog(this).show(this.url) { url ->
                this.url = url
                binding.activityFormImage.tryToLoadImage(url)
            }
        }
    }

    private fun configureSaveButton() {
        val saveButton = binding.activityProductFormSaveButton
        val dao = ProductsDao()
        saveButton.setOnClickListener {
            val newProduct = createNewProduct()
            dao.add(newProduct)
            finish()
        }
    }

    private fun createNewProduct(): Product {
        val nameField = binding.activityProductFormName
        val name = nameField.text.toString()
        val descriptionField = binding.activityProductFormDescription
        val description = descriptionField.text.toString()
        val valueField = binding.activityProductFormValue
        val valueText = valueField.text.toString()
        val value = if (valueText.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valueText.toString())
        }

        return Product(nome = name, descricao = description, valor = value, imagem = url)

    }
}