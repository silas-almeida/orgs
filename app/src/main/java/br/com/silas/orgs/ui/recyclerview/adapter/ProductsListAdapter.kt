package br.com.silas.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.silas.orgs.databinding.ProductItemBinding
import br.com.silas.orgs.extensions.tryToLoadImage
import br.com.silas.orgs.models.Product
import java.text.NumberFormat
import java.util.*

class ProductsListAdapter(
    private val context: Context,
    products: List<Product>
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    class ViewHolder(private val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            val name = binding.productItemName
            name.text = product.name
            val description = binding.productItemDescription
            description.text = product.description
            val value = binding.productItemValue
            val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            value.text = currencyFormat.format(product.value)

            val visibility = if (product.imageUrl != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.imageView.visibility = visibility
            binding.imageView.tryToLoadImage(product.imageUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ProductItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)

    }

    override fun getItemCount(): Int = products.size

    fun update(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

}
