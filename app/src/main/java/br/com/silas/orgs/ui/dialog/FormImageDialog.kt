package br.com.silas.orgs.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import br.com.silas.orgs.databinding.ImageFormBinding
import br.com.silas.orgs.extensions.tryToLoadImage

class FormImageDialog(private val context: Context) {
    fun show(
        standardUrl: String? = null,
        whenLoadedImage: (url: String) -> Unit
    ) {
        ImageFormBinding.inflate(LayoutInflater.from(context)).apply {
            standardUrl?.let {
                dialogImage.tryToLoadImage(it)
                urlTextInputEditText.setText(it)
            }

            dialogButton.setOnClickListener {
                val url = urlTextInputEditText.text.toString()
                dialogImage.tryToLoadImage(url)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = urlTextInputEditText.text.toString()
                    whenLoadedImage(url)
                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .show()
        }

    }
}