package mustafaozhan.github.com.cosmeticscan.main.fragment

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_main.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.base.BaseMvvmFragment
import mustafaozhan.github.com.cosmeticscan.camera.CameraFragment

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

        setListeners()
    }


    private fun setListeners() {
        cardViewCamera.setOnClickListener { getBaseActivity().replaceFragment(CameraFragment.newInstance(), true) }
        cardViewManual.setOnClickListener{ getBaseActivity().replaceFragment(CameraFragment.newInstance(), true) }
    }


}