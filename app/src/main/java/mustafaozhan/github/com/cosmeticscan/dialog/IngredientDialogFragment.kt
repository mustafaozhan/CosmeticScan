package mustafaozhan.github.com.cosmeticscan.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.dialog_ingredient.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.base.model.Ingredient

/**
 * Created by Mustafa Ozhan on 2018-08-05.
 */
class IngredientDialogFragment : DialogFragment() {

    companion object {
        private const val INGREDIENT = "INGREDIENT"
        fun newInstance(ingredient: Ingredient): IngredientDialogFragment {
            val args = Bundle()
            args.putSerializable(INGREDIENT, ingredient)
            val fragment = IngredientDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    var ingredient: Ingredient? = null
    private var uri: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_ingredient,
                container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        ingredient = arguments?.getSerializable(INGREDIENT) as Ingredient?

        initViews()
        setListeners()
    }

    private fun setListeners() {
        btnDialogOk.setOnClickListener { dismiss() }
    }

    private fun initViews() {
        ingredient?.apply {
            txtDialogName.text = name
            txtDialogInformation.text = information
            txtDialogRating.text = rating
//            txtDialogCategories.text = category
        }
        txtDialogRating.apply {
            when (text) {
                "Best" -> setTextColor(ContextCompat.getColor(context, R.color.blue))
                "Good" -> setTextColor(ContextCompat.getColor(context, R.color.green))
                "Average" -> setTextColor(ContextCompat.getColor(context, R.color.yellow))
                "Poor" -> setTextColor(ContextCompat.getColor(context, R.color.red))
            }
        }
    }


}