package mustafaozhan.github.com.cosmeticscan.ingredients

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_ingredients.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.base.BaseMvvmFragment
import mustafaozhan.github.com.cosmeticscan.manual.adapter.IngredientAdapter

/**
 * Created by Mustafa Ozhan on 2018-08-04.
 */
class IngredientsFragment : BaseMvvmFragment<IngredientsFragmentViewModel>() {


    companion object {
        const val INGREDIENTS="INGREDIENTS"

        fun newInstance(ingredients:ArrayList<String>): IngredientsFragment {
            val args = Bundle()
            args.putStringArrayList(INGREDIENTS, ingredients)
            val fragment = IngredientsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val ingredientAdapter: IngredientAdapter by lazy { IngredientAdapter() }

    override fun getViewModelClass(): Class<IngredientsFragmentViewModel> = IngredientsFragmentViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.fragment_ingredients

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViews()

    }



    private fun initViews() {
        context?.let {
            mLoadingView.smoothToShow()
            ingredientRecyclerView.layoutManager = LinearLayoutManager(it)
            ingredientRecyclerView.adapter = ingredientAdapter
        }
    }


}