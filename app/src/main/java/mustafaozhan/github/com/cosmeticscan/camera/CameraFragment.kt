package mustafaozhan.github.com.cosmeticscan.camera

import android.os.Bundle
import android.view.View
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.base.BaseMvvmFragment

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
class CameraFragment : BaseMvvmFragment<CameraFragmentViewModel>() {

    companion object {
        fun newInstance(): CameraFragment = CameraFragment()
    }

    override fun getViewModelClass(): Class<CameraFragmentViewModel> = CameraFragmentViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.fragment_camera



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }




}