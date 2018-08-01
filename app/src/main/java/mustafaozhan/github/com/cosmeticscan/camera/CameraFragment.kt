package mustafaozhan.github.com.cosmeticscan.camera

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import com.livinglifetechway.quickpermissions.annotations.WithPermissions
import kotlinx.android.synthetic.main.fragment_camera.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.base.BaseMvvmFragment
import mustafaozhan.github.com.cosmeticscan.old.ui.activities.IngredientsActivity
import org.jetbrains.anko.doAsync
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
class CameraFragment : BaseMvvmFragment<CameraFragmentViewModel>(),  SurfaceHolder.Callback, View.OnClickListener {

    companion object {
        fun newInstance(): CameraFragment = CameraFragment()
    }

    override fun getViewModelClass(): Class<CameraFragmentViewModel> = CameraFragmentViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.fragment_camera

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCamera()
    }

    @WithPermissions([(Manifest.permission.CAMERA)])
    private fun initCamera() {

    }

    var data: String? = null
    var counter = 0
    private var textRecognizer: TextRecognizer? = null
    private var cameraSource: CameraSource? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_camera_old, container, false)
        activity!!.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        doAsync {
            viewModel.getIngredients()
        }

        return fragmentView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()
        setRecognition()
    }

    private fun setRecognition() {
        mProgressBar.progress
        if (!textRecognizer!!.isOperational) {
            Log.w("OldMainActivity", "Detector dependencies are not yet available")
        } else {

            cameraSource = CameraSource.Builder(activity, textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    //  .setRequestedPreviewSize(1280, 1324)
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


                            Observable.create(Observable.OnSubscribe<String> { subscriber ->
                                subscriber.onNext(stringBuilder.toString())
                            }).debounce(500, TimeUnit.MILLISECONDS)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe { text ->

                                        doAsync {
                                            val temp = viewModel.ingredients.filter { it.name }(text.toString(), txtScan.text.toString())

                                            activity!!.runOnUiThread {

                                                data = temp
                                                counter = (0 until temp!!.length).count { temp[it] == ',' }
                                                if (counter != 0)
                                                    txtScan.text = "We found $counter ingredient(s) click for details"
                                            }
                                        }


                                    }


                        }
                    }
                }
            })
        }
    }

    private fun init() {
        btnRefresh.setOnClickListener(this)
        surfaceView.holder.addCallback(this)
        txtScan.setOnClickListener(this)
        textRecognizer = TextRecognizer.Builder(activity).build()
    }

    override fun onClick(view: View?) {
        when (view) {

            txtScan -> {
                if (txtScan.text.toString().length > 1) {
                    val intent = Intent(context, IngredientsActivity::class.java)
                    intent.putExtra("data", data)
                    startActivity(intent)
                }
            }
            btnRefresh -> {
                txtScan.text = ""
                data = null
                counter = 0
            }
        }


    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {

//        cameraSource?.start(surfaceView.holder)

    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {

    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        cameraSource?.stop()
    }



    override fun onResume() {
        super.onResume()
        txtScan.text = ""
        data = null
        counter = 0

    }

}