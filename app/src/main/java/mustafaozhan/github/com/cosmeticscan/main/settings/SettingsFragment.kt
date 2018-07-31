package mustafaozhan.github.com.cosmeticscan.main.settings

import android.os.Bundle
import android.view.View
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.base.BaseMvvmFragment

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
class SettingsFragment : BaseMvvmFragment<SettingsFragmentViewModel>() {

    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
    }

    override fun getViewModelClass(): Class<SettingsFragmentViewModel> = SettingsFragmentViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.fragment_settings


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViews()
        setListeners()
    }

    private fun initViews() {



    }


    private fun setListeners() {

    }

    private fun updateUi(update: Boolean = false, byName: Boolean = false, value: Int = 0, name: String = "") {

    }


}