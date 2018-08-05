package mustafaozhan.github.com.cosmeticscan.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import mustafaozhan.github.com.cosmeticscan.main.listener.PagePositionChangeListener

/**
 * Created by Mustafa Ozhan on 2018-08-04.
 */
class MainActivityViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager), ViewPager.OnPageChangeListener {

    private var lastSelectedPagePosition = 0
    private val mFragmentList = ArrayList<Fragment>()
    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFrag(fragment: Fragment) {
        mFragmentList.add(fragment)
    }
    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        if (lastSelectedPagePosition != -1 && mFragmentList[lastSelectedPagePosition] is PagePositionChangeListener) {
            (mFragmentList[lastSelectedPagePosition] as PagePositionChangeListener).onPagePositionChange(position)
        }

        if (mFragmentList[position] is PagePositionChangeListener) {
            (mFragmentList[position] as PagePositionChangeListener).onPagePositionChange(position)
        }

        lastSelectedPagePosition = position
    }

}