package mustafaozhan.github.com.cosmeticscan.ui.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
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

    private val URL = "databaseUrl"
    companion object {
        val PERMISSIONS_REQUEST_CODE = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       checkPermissionsAndInit()

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


    private fun checkPermissionsAndInit() {
        val permission = Manifest.permission.CAMERA

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) { //checking if we  have permissions
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showError()


            } else {
                ActivityCompat.requestPermissions(this, arrayOf<String>(permission), PERMISSIONS_REQUEST_CODE)
            }
        } else {
            init() //opening filemanager fragment
        }
    }

    private fun showError() {
        val permission = Manifest.permission.CAMERA
        Toast.makeText(this, "Allow camera to read text", Toast.LENGTH_SHORT).show()
        ActivityCompat.requestPermissions(this, arrayOf<String>(permission), PERMISSIONS_REQUEST_CODE)

    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) { // geting result from permissinon request
        when (requestCode) {
            PERMISSIONS_REQUEST_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    init()
                } else {
                    showError()

                }
            }
        }
    }

    private fun init() {
        setSupportActionBar(toolbar)
        setupViewPager(viewpager)
        tabs.setupWithViewPager(viewpager)
    }

}