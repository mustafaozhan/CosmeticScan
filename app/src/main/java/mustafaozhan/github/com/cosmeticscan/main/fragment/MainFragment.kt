package mustafaozhan.github.com.cosmeticscan.main.fragment

import android.os.Bundle
import android.view.View
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.base.BaseMvvmFragment

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
class MainFragment : BaseMvvmFragment<MainFragmentViewModel>() {

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    override fun getViewModelClass(): Class<MainFragmentViewModel> = MainFragmentViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.fragment_main



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViews()
        setListeners()
        initData()
    }


    private fun initData() {


    }

    private fun initViews() {

    }

    private fun updateUi() {

    }

    private fun setListeners() {

    }


}