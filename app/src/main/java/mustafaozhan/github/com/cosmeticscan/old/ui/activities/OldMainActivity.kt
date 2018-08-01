package mustafaozhan.github.com.cosmeticscan.old.ui.activities

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
import kotlinx.android.synthetic.main.activity_main_old.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.old.model.Ingredient
import mustafaozhan.github.com.cosmeticscan.old.ui.adapters.MyViewPagerAdapter
import mustafaozhan.github.com.cosmeticscan.old.ui.fragments.CameraFragmentOld
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class OldMainActivity : AppCompatActivity() {


    companion object {
        val PERMISSIONS_REQUEST_CODE = 0
        private val URL = "databaseUrl"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_old)


        checkPermissionsAndInit()

        val settings = getSharedPreferences("firstTime", 0)
        val firsTime = settings.getBoolean("firstTime", true)

        if (firsTime)
            doAsync {
//                val jsonStr = HttpHandler().makeServiceCall(URL)
//                val myGSon = Gson()
//                val response = myGSon.fromJson<List<Ingredient>>(jsonStr)
//
//                uiThread {
//                    database.insertIngredientList(response)
//                    settings.edit().putBoolean("firstTime", false).apply()
//                }

            }

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = MyViewPagerAdapter(supportFragmentManager)

        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(adapter)

    }

    private fun checkPermissionsAndInit() {
        val permission = Manifest.permission.CAMERA

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) { //checking if we  have permissions
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showError()


            } else {
                ActivityCompat.requestPermissions(this, arrayOf(permission), PERMISSIONS_REQUEST_CODE)
            }
        } else {
            init() //opening filemanager fragment
        }
    }

    private fun showError() {
        val permission = Manifest.permission.CAMERA
        Toast.makeText(this, "Allow camera to read text", Toast.LENGTH_SHORT).show()
        ActivityCompat.requestPermissions(this, arrayOf(permission), PERMISSIONS_REQUEST_CODE)

    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
    ) { // geting result from permissinon request
        when (requestCode) {
            PERMISSIONS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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