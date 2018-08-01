package mustafaozhan.github.com.cosmeticscan.manual.adapter


import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row.view.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.base.model.Ingredient
import mustafaozhan.github.com.mycurrencies.base.adapter.BaseRecyclerViewAdapter
import mustafaozhan.github.com.mycurrencies.base.adapter.BaseViewHolder

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
class IngredientAdapter : BaseRecyclerViewAdapter<Ingredient>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Ingredient> =
            RatesViewHolder(getViewHolderView(parent, R.layout.row))

    class RatesViewHolder(itemView: View) : BaseViewHolder<Ingredient>(itemView) {
        override fun bind(item: Ingredient) {
            itemView.txtName.text = item.name
            itemView.txtRating.text = item.rating

            when (itemView.txtRating.text) {
                "Best" -> itemView.txtRating.setTextColor(ContextCompat.getColor(itemView.context, R.color.blue))
                "Good" -> itemView.txtRating.setTextColor(ContextCompat.getColor(itemView.context, R.color.green))
                "Average" -> itemView.txtRating.setTextColor(ContextCompat.getColor(itemView.context, R.color.yellow))
                "Poor" -> itemView.txtRating.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))
            }
        }
    }
}