package br.com.silas.orgs.ui.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.silas.orgs.R
import br.com.silas.orgs.dao.ProductsDao
import br.com.silas.orgs.databinding.ActivityProductsListBinding
import br.com.silas.orgs.ui.dialog.FormImageDialog
import br.com.silas.orgs.ui.recyclerview.adapter.ProductsListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductsListActivity : AppCompatActivity(R.layout.activity_products_list) {

    private val dao = ProductsDao()
    private val adapter = ProductsListAdapter(context = this, products = dao.searchAll())
    private val binding by lazy {
        ActivityProductsListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureRecyclerView()
        configureFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.searchAll())
    }

    private fun configureFab() {
        val fab = binding.productsListFab
        fab.setOnClickListener {
            setClickIntent()
        }
    }

    private fun setClickIntent() {
        val intent = Intent(this, ProductFormActivity::class.java)
        startActivity(intent)
    }

    private fun configureRecyclerView() {
        val recyclerView = binding.productsListRecyclerView
        recyclerView.adapter = adapter
    }
}