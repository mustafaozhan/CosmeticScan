package mustafaozhan.github.com.cosmeticscan.ui.activities

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.common.model.Ingredient
import mustafaozhan.github.com.cosmeticscan.common.model.database
import mustafaozhan.github.com.cosmeticscan.ui.adapters.MyViewPagerAdapter
import mustafaozhan.github.com.cosmeticscan.ui.fragments.CameraFragment
import mustafaozhan.github.com.cosmeticscan.ui.fragments.ManualFragment
import mustafaozhan.github.com.cosmeticscan.utils.HttpHandler
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    private val URL = "databaseURL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        setupViewPager(viewpager)
        tabs.setupWithViewPager(viewpager)

        val settings = getSharedPreferences("firstTime", 0)
        val firsTime = settings.getBoolean("firstTime", true)

        if (firsTime)
            doAsync {
                val jsonStr = HttpHandler().makeServiceCall(URL)
                val myGSon = Gson()
                val response = myGSon.fromJson<List<Ingredient>>(jsonStr)

                uiThread {
                    database.insertIngredientList(response)
                    settings.edit().putBoolean("firstTime", false).commit()
                }

            }
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(CameraFragment(), resources.getString(R.string.camera))
        adapter.addFrag(ManualFragment(), resources.getString(R.string.manual))

        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(adapter)

    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//
//            R.id.settings ->
//                return true
//
//            else -> {
//            }
//        }
//
//        return true
//    }


}