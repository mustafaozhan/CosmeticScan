package mustafaozhan.github.com.cosmeticscan.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import java.util.*

/**
Created by Mustafa Özhan on 7/23/17 at 1:51 PM on Linux <3.

 */
class MyViewPagerAdapter internal constructor(manager: FragmentManager) : FragmentPagerAdapter(manager),ViewPager.OnPageChangeListener {


    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    private var lastSelectedPagePosition = 0

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    internal fun addFrag(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentTitleList[position]

    }
    override fun onPageScrollStateChanged(state: Int) {
//                Log.d("Scroll state changed","Fragment $state")
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        //       Log.d("Scrolled","Fragment $position")
    }

    override fun onPageSelected(position: Int) {


    }


}