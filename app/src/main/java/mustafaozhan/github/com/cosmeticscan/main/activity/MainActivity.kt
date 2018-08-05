package mustafaozhan.github.com.cosmeticscan.main.activity

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.View
import de.mateware.snacky.Snacky
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.base.BaseFragment
import mustafaozhan.github.com.cosmeticscan.base.BaseMvvmActivity
import mustafaozhan.github.com.cosmeticscan.camera.CameraFragment
import mustafaozhan.github.com.cosmeticscan.extensions.fadeIO
import mustafaozhan.github.com.cosmeticscan.main.adapter.MainActivityViewPagerAdapter
import mustafaozhan.github.com.cosmeticscan.main.fragment.MainFragment
import mustafaozhan.github.com.cosmeticscan.manual.ManualFragment
import mustafaozhan.github.com.cosmeticscan.settings.SettingsFragment

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
class MainActivity : BaseMvvmActivity<MainActivityViewModel>() {

    private var doubleBackToExitPressedOnce = false

    override fun getDefaultFragment(): BaseFragment = MainFragment.newInstance()

    override fun getViewModelClass(): Class<MainActivityViewModel> = MainActivityViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        val mAdapter = MainActivityViewPagerAdapter(supportFragmentManager)
        mAdapter.apply {
            addFrag(CameraFragment())
            addFrag(MainFragment())
            addFrag(ManualFragment())
        }
        content.apply {
            adapter = mAdapter
            currentItem = 1
        }
    }

    override fun onBackPressed() {
        when {
            webView.visibility == View.VISIBLE -> {
                webView.apply {
                    fadeIO(false)
                    visibility = View.GONE
                }
            }
            else -> {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed()
                    return
                }
                doubleBackToExitPressedOnce = true
                snacky("Please click BACK again to exit")
                Handler().postDelayed({
                    doubleBackToExitPressedOnce = false
                }, 2000)
            }

        }

    }

    fun snacky(text: String, hasAction: Boolean = false, actionText: String = "") {

        val mySnacky = Snacky.builder()
                .setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setText(text)
                .setIcon(R.mipmap.ic_launcher)
                .setActivity(this)
                .setDuration(Snacky.LENGTH_SHORT)

        if (hasAction) {
            mySnacky.setActionText(actionText.toUpperCase())
                    .setActionTextColor(ContextCompat.getColor(this, R.color.cyan_700))
                    .setActionTextTypefaceStyle(Typeface.BOLD)
                    .setActionClickListener { replaceFragment(SettingsFragment.newInstance(), true) }

        }
        mySnacky.build().show()
    }

     fun showGithub() {
        webView.apply {
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
}