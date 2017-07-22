package mustafaozhan.github.com.cosmeticscan.ui.adapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row.view.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.common.model.Ingredient

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
            val ORANGE = "#FF3D00"
            val YELLOW = "#FFEA00"
            val BLUE = "#2979FF"
            val GREEN = "#00E676"
            itemView.txtName.text = ingredient.name
            itemView.txtInformation.text = ingredient.information
            itemView.txtCategory.text = "Category: " + ingredient.category
            itemView.txtRating.text = ingredient.rating

            when (itemView.txtRating.text) {
                "Best" -> itemView.txtRating.setTextColor(Color.parseColor(BLUE))
                "Good" -> itemView.txtRating.setTextColor(Color.parseColor((GREEN)))
                "Average" -> itemView.txtRating.setTextColor(Color.parseColor(YELLOW))
                "Poor" -> itemView.txtRating.setTextColor(Color.parseColor(RED))
//                "Good"->itemView.txtRating.setTextColor(Color.parseColor(BLUE))
            }


        }
    }
}