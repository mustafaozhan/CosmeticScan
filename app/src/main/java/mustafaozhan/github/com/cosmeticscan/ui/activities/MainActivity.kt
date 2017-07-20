package mustafaozhan.github.com.cosmeticscan.ui.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.common.model.Ingredient
import mustafaozhan.github.com.cosmeticscan.common.model.database
import mustafaozhan.github.com.cosmeticscan.ui.fragments.CameraFragment
import mustafaozhan.github.com.cosmeticscan.ui.fragments.ManualFragment
import mustafaozhan.github.com.cosmeticscan.utils.HttpHandler
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*


class MainActivity : AppCompatActivity() {

    private val URL = "databaseUrl"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        setSupportActionBar(toolbar)
           setupViewPager(viewpager)
          tabs.setupWithViewPager(viewpager)


        val settings = getSharedPreferences("firstTime", 0)
        var firsTime = settings.getBoolean("firstTime", true)

        if (firsTime)
            doAsync {
                val jsonStr = HttpHandler().makeServiceCall(URL)
                val gson = Gson()
                val response = gson.fromJson<List<Ingredient>>(jsonStr)

                uiThread {
                    database.insertIngredientList(response)
                    settings.edit().putBoolean("firstTime", false).commit()
                }

            }

//        println("================>"+database.getIngredientList())


    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(CameraFragment(), resources.getString(R.string.camera))
        adapter.addFrag(ManualFragment(), resources.getString(R.string.manual))

        viewPager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.settings ->
                return true

            else -> {
            }
        }

        return true
    }


    private inner class ViewPagerAdapter internal constructor(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

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
    }


}