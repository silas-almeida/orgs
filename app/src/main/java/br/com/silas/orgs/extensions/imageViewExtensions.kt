package br.com.silas.orgs.extensions

import android.widget.ImageView
import br.com.silas.orgs.R
import coil.load

fun ImageView.tryToLoadImage(url: String? = null){
    load(url){
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}