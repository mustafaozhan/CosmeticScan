package mustafaozhan.github.com.cosmeticscan.ui.adapters

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
import mustafaozhan.github.com.cosmeticscan.common.model.Ingredient
import java.util.*


/**
Created by Mustafa Özhan on 7/14/17 at 2:45 PM on Linux.

 */
class IngredientAdapter(val ingredientList: List<Ingredient>) :
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

        fun bindForecast(ingredient: Ingredient) {
            val RED = "#FF1744"
            val YELLOW = "#FFEA00"
            val BLUE = "#2979FF"
            val GREEN = "#00E676"
            itemView.txtName.text = ingredient.name
//            itemView.txtInformation.text = ingredient.information
//            itemView.txtCategory.text = "Category: " + ingredient.category
            itemView.txtRating.text = ingredient.rating

            when (itemView.txtRating.text) {
                "Best" -> itemView.txtRating.setTextColor(Color.parseColor(BLUE))
                "Good" -> itemView.txtRating.setTextColor(Color.parseColor((GREEN)))
                "Average" -> itemView.txtRating.setTextColor(Color.parseColor(YELLOW))
                "Poor" -> itemView.txtRating.setTextColor(Color.parseColor(RED))
//                "Good"->itemView.txtRating.setTextColor(Color.parseColor(BLUE))
            }

            itemView.setOnClickListener {

                // custom dialog
                val dialog = Dialog(itemView.context)
                dialog.setContentView(R.layout.mydialog)
                // Custom Android Allert Dialog Title
                dialog.setTitle("Ingredients informations")

                dialog.txtDialogName.text = ingredient.name
                dialog.txtDialogRating.text = ingredient.rating
                dialog.txtDialogInformation.text = ingredient.information
                dialog.txtDialogCategories.text = "Catagories: "+ingredient.category

                when (dialog.txtDialogRating.text) {
                    "Best" -> dialog.txtDialogRating.setTextColor(Color.parseColor(BLUE))
                    "Good" -> dialog.txtDialogRating.setTextColor(Color.parseColor((GREEN)))
                    "Average" -> dialog.txtDialogRating.setTextColor(Color.parseColor(YELLOW))
                    "Poor" -> dialog.txtDialogRating.setTextColor(Color.parseColor(RED))
//                "Good"->itemView.txtRating.setTextColor(Color.parseColor(BLUE))
                }


                val dialogButtonTranslate = dialog.findViewById<View>(R.id.btnDialogTranslate) as Button
                val dialogButtonOk = dialog.findViewById<View>(R.id.btnDialogOk) as Button
                // Click cancel to dismiss android custom dialog box
                dialogButtonTranslate.setOnClickListener {

                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://translate.google.com/#en/${Locale.getDefault().displayLanguage}/${dialog.txtDialogInformation.text.toString()}"))
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