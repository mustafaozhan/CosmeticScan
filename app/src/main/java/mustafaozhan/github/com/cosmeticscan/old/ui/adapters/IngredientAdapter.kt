package mustafaozhan.github.com.cosmeticscan.old.ui.adapters

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.mydialog.*
import kotlinx.android.synthetic.main.row.view.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.old.model.Ingredient
import java.util.*


/**
Created by Mustafa Özhan on 7/14/17 at 2:45 PM on Linux.

 */
class IngredientAdapter(private val ingredientList: List<Ingredient>) :
        RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(ingredientList[position])
    }

    override fun getItemCount() = ingredientList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bindForecast(ingredient: Ingredient) {

            itemView.txtName.text = ingredient.name
//            itemView.txtInformation.text = ingredient.information
//            itemView.txtCategory.text = "Category: " + ingredient.category
            itemView.txtRating.text = ingredient.rating



            itemView.setOnClickListener {

                // custom dialog
                val dialog = Dialog(itemView.context)
                dialog.setContentView(R.layout.mydialog)
                // Custom Android Alert Dialog Title
                dialog.setTitle("Ingredients information")

                dialog.txtDialogName.text = ingredient.name
                dialog.txtDialogRating.text = ingredient.rating
                dialog.txtDialogInformation.text = ingredient.information
                dialog.txtDialogCategories.text = "Catagories: " + ingredient.category



                val dialogButtonTranslate = dialog.findViewById<View>(R.id.btnDialogTranslate) as Button
                val dialogButtonOk = dialog.findViewById<View>(R.id.btnDialogOk) as Button
                // Click cancel to dismiss android custom dialog box
                dialogButtonTranslate.setOnClickListener {

                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://translate.google.com/#en/${Locale.getDefault().displayLanguage}/${dialog.txtDialogInformation.text}"))
                    startActivity(dialog.context, browserIntent, null)

                    dialog.dismiss()
                }

                // Your android custom dialog ok action
                // Action for custom dialog ok button click
                dialogButtonOk.setOnClickListener { dialog.dismiss() }

                dialog.show()
            }


        }
    }
}