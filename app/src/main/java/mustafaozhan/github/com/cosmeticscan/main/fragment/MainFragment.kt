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
import mustafaozhan.github.com.cosmeticscan.extensions.fadeIO
import mustafaozhan.github.com.cosmeticscan.main.activity.MainActivity
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

        doAsync {
            initData()
            uiThread {
                mLoadingView.smoothToHide()
            }
        }
    }

    private fun initData() {
        viewModel.loadPreferences()
        if (viewModel.firstRun)
            viewModel.getAllIngredients()
    }


    private fun setListeners() {
        cardViewCamera.setOnClickListener { (activity as MainActivity).content.currentItem = 0 }
        cardViewManual.setOnClickListener { (activity as MainActivity).content.currentItem = 2 }
        carViewGitHub.setOnClickListener { showGithub() }
        carViewMail.setOnClickListener { sendFeedBack() }
        carViewPlayStore.setOnClickListener { showRateDialog() }
    }

    override fun onPause() {
        viewModel.savePreferences()
        super.onPause()
    }

    override fun onResume() {
        viewModel.loadPreferences()
        super.onResume()
    }

    private fun showGithub() {
        webView.apply {
            bringToFront()
            var newUserAgent: String? = settings.userAgentString
            try {
                val ua = settings.userAgentString
                val androidOSString = settings.userAgentString.substring(ua.indexOf("("), ua.indexOf(")") + 1)
                newUserAgent = settings.userAgentString.replace(androidOSString, "(X11; Linux x86_64)")
            } catch (e: Exception) {
                e.printStackTrace()
            }

            settings.apply {
                loadWithOverviewMode = true
                javaScriptEnabled = true
                useWideViewPort = true
                setSupportZoom(true)
                builtInZoomControls = true
                displayZoomControls = false
                userAgentString = newUserAgent
            }
            loadUrl("https://github.com/mustafaozhan/CosmeticScan")
            fadeIO(true)
            bringToFront()
            visibility = View.VISIBLE
        }
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