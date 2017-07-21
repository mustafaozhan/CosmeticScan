package mustafaozhan.github.com.cosmeticscan.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import kotlinx.android.synthetic.main.fragment_camera.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.common.model.MyDatabaseOpenHelper
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.io.IOException
import java.util.concurrent.TimeUnit


class CameraFragment : Fragment() {

    val RED = "#FF1744"
    val ORANGE = "#FF3D00"
    val YELLOW = "#FFEA00"
    val BLUE = "#2979FF"
    val GREEN = "#00E676"


    internal lateinit var cameraSource: CameraSource
    internal val RequestCameraPermissionID = 1001

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater!!.inflate(R.layout.fragment_camera, container, false)




        return fragmentView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mProgressBar.progress





        init()
    }

    private fun init() {

        val textRecognizer = TextRecognizer.Builder(activity).build()




        if (!textRecognizer.isOperational) {
            Log.w("MainActivity", "Detector dependencies are not yet available")
        } else {

            cameraSource = CameraSource.Builder(activity, textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build()
            surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
                override fun surfaceCreated(surfaceHolder: SurfaceHolder) {

                    try {
                        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(activity,
                                    arrayOf(Manifest.permission.CAMERA),
                                    RequestCameraPermissionID)
                            return
                        }
                        cameraSource.start(surfaceView.holder)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }

                override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {

                }

                override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
                    cameraSource.stop()
                }
            })

            textRecognizer.setProcessor(object : Detector.Processor<TextBlock> {
                override fun release() {

                }

                override fun receiveDetections(detections: Detector.Detections<TextBlock>) {

                    val items = detections.detectedItems
                    if (items.size() != 0) {
                        txtScan.post {
                            val stringBuilder = StringBuilder()
                            for (i in 0..items.size() - 1) {
                                val item = items.valueAt(i)
                                stringBuilder.append(item.value)
                            }
                           // txtScan.text = stringBuilder.toString()


                            Observable.create(Observable.OnSubscribe<String> { subscriber ->
                                subscriber.onNext(stringBuilder.toString())
                            }).debounce(500, TimeUnit.MILLISECONDS)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe({
                                        text ->
                                        txtScan.text = MyDatabaseOpenHelper.getInstance(context).searchInDatabase(text.toString(),txtScan.text.toString())
                                    })



                        }
                    }
                }
            })
        }

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            RequestCameraPermissionID -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return
                    }
                    try {
                        cameraSource.start(surfaceView.holder)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        txtScan.text = ""
    }
}
