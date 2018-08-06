package mustafaozhan.github.com.cosmeticscan.main.fragment


import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.base.BaseMvvmFragment
import mustafaozhan.github.com.cosmeticscan.camera.CameraFragment
import mustafaozhan.github.com.cosmeticscan.extensions.fadeIO
import mustafaozhan.github.com.cosmeticscan.main.activity.MainActivity
import mustafaozhan.github.com.cosmeticscan.manual.ManualFragment
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

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


        initData()


    }

    private fun initData() {
        viewModel.loadPreferences()
        if (viewModel.firstRun) {
            doAsync {
                viewModel.getAllIngredients()
                viewModel.firstRun = false
                uiThread {
                    mLoadingView.smoothToHide()
                }
            }
        } else {
            mLoadingView.smoothToHide()
        }
    }


    private fun setListeners() {
        imgCamera.setOnClickListener { getBaseActivity().replaceFragment(CameraFragment.newInstance(), true, true) }
        imgLeft.setOnClickListener { getBaseActivity().replaceFragment(CameraFragment.newInstance(), true, true) }
        imgRight.setOnClickListener { getBaseActivity().replaceFragment(ManualFragment.newInstance(), true) }
        imgManual.setOnClickListener { getBaseActivity().replaceFragment(ManualFragment.newInstance(), true) }
        imgGitHub.setOnClickListener { (activity as MainActivity).showGithub() }
        imgMail.setOnClickListener { sendFeedBack() }
        imgPlayStore.setOnClickListener { showRateDialog() }
    }

    override fun onPause() {
        viewModel.savePreferences()
        super.onPause()
    }

    override fun onResume() {
        viewModel.loadPreferences()
        super.onResume()
    }


    private fun showRateDialog() {
        val builder = AlertDialog.Builder(context, R.style.AlertDialogCustom)
                .setTitle("Support us !")
                .setMessage("Please, rate and commend to the app at Google Play Store")
                .setPositiveButton("RATE") { _, _ ->
                    var link = "market://details?id="
                    try {
                        context?.packageManager?.getPackageInfo(context?.packageName + ":Cosmetic Scan", 0)
                    } catch (e: PackageManager.NameNotFoundException) {
                        link = "https://play.google.com/store/apps/details?id="
                    }
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link + context?.packageName)))
                }
                .setNegativeButton("CANCEL", null)
        builder.show()
    }

    private fun sendFeedBack() {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/email"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("mr.mustafa.ozhan@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Feedback for My Currencies")
            putExtra(Intent.EXTRA_TEXT, "Dear Developer," + "")
            startActivity(Intent.createChooser(this, "Send Feedback:"))
        }
    }


}