package mustafaozhan.github.com.cosmeticscan.camera

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.View
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import com.livinglifetechway.quickpermissions.annotations.WithPermissions
import kotlinx.android.synthetic.main.fragment_camera.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.base.BaseMvvmFragment
import org.jetbrains.anko.doAsync
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
class CameraFragment : BaseMvvmFragment<CameraFragmentViewModel>(), SurfaceHolder.Callback {

    companion object {
        fun newInstance(): CameraFragment = CameraFragment()
    }

    override fun getViewModelClass(): Class<CameraFragmentViewModel> = CameraFragmentViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.fragment_camera

    private var textRecognizer: TextRecognizer? = null
    private var cameraSource: CameraSource? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doAsync {
            viewModel.getIngredients()
        }
        init()
        setRecognition()
        setListeners()

    }

    private fun setListeners() {
        txtScan.setOnClickListener {
            //            if (txtScan.text.toString().length > 1) {
//                val intent = Intent(context, IngredientsActivity::class.java)
//                intent.putExtra("data", data)
//                startActivity(intent)
//            }
        }
        btnRefresh.setOnClickListener {
            txtScan.text = ""
        }
    }


    private fun init() {
        surfaceView.holder.addCallback(this)
        textRecognizer = TextRecognizer.Builder(activity).build()
    }

    private fun setRecognition() {


        cameraSource = CameraSource.Builder(activity, textRecognizer)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(2.0f)
                .setAutoFocusEnabled(true)
                .build()



        textRecognizer!!.setProcessor(object : Detector.Processor<TextBlock> {
            override fun release() {

            }

            @SuppressLint("SetTextI18n")
            override fun receiveDetections(detections: Detector.Detections<TextBlock>) {

                val items = detections.detectedItems
                if (items.size() != 0) {
                    txtScan.post {
                        val stringBuilder = StringBuilder()
                        (0 until items.size())
                                .map { items.valueAt(it) }
                                .forEach { stringBuilder.append(it.value) }
                        // txtScan.text = stringBuilder.toString()


                        Observable.unsafeCreate(Observable.OnSubscribe<String> { subscriber ->
                            subscriber.onNext(stringBuilder.toString())
                        }).debounce(500, TimeUnit.MILLISECONDS)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe { text ->
                                    doAsync {
                                        viewModel.search(text)

                                        if (viewModel.counter != 0)
                                            txtScan.text = "We found ${viewModel.counter} ingredient(s) click for details"
                                    }
                                }
                    }
                }
            }
        })

    }

    @SuppressLint("MissingPermission")
    @WithPermissions([(Manifest.permission.CAMERA)])
    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        try {


            cameraSource?.start(surfaceView.holder)
        } catch (e: Exception) {

        }
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {

    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        cameraSource?.stop()
    }


    override fun onResume() {
        super.onResume()
        txtScan.text = ""

    }

}