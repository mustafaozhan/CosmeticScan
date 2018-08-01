package mustafaozhan.github.com.cosmeticscan.manual

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jakewharton.rxbinding2.widget.textChanges
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_manual.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.base.BaseMvvmFragment
import mustafaozhan.github.com.cosmeticscan.extensions.reObserve
import mustafaozhan.github.com.cosmeticscan.manual.adapter.IngredientAdapter
import org.jetbrains.anko.doAsync

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
class ManualFragment : BaseMvvmFragment<ManualFragmentViewModel>() {

    companion object {
        fun newInstance(): ManualFragment = ManualFragment()
    }

    private val ingredientAdapter: IngredientAdapter by lazy { IngredientAdapter() }

    override fun getViewModelClass(): Class<ManualFragmentViewModel> = ManualFragmentViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.fragment_manual

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doAsync {
            viewModel.getIngredients()
        }
        initViews()
        initData()
    }

    private fun initData() {
        eTxtSearch.textChanges()
                .subscribe {
                    val text = it
                    ingredientAdapter.refreshList(viewModel.ingredients.filter { it.name.contains(text) }.toMutableList())
                    mLoadingView.smoothToHide()
                }
    }

    private fun initViews() {
        context?.let {
            mLoadingView.smoothToShow()
            ingredientRecyclerView.layoutManager = LinearLayoutManager(it)
            ingredientRecyclerView.adapter = ingredientAdapter
        }
    }


}