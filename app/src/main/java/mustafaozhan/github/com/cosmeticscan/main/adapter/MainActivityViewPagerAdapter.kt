package mustafaozhan.github.com.cosmeticscan.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Mustafa Ozhan on 2018-08-04.
 */
class MainActivityViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

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

}