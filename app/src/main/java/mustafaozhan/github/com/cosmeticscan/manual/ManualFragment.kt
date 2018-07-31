package mustafaozhan.github.com.cosmeticscan.manual

import android.os.Bundle
import android.view.View
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.base.BaseMvvmFragment

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
class ManualFragment : BaseMvvmFragment<ManualFragmentViewModel>() {

    companion object {
        fun newInstance(): ManualFragment = ManualFragment()
    }

    override fun getViewModelClass(): Class<ManualFragmentViewModel> = ManualFragmentViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.fragment_manual



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }




}