package mustafaozhan.github.com.cosmeticscan.extensions

import android.view.animation.AnimationUtils
import android.webkit.WebView
import mustafaozhan.github.com.cosmeticscan.R

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
fun WebView.fadeIO(isIn: Boolean) {
    if (isIn)
        this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in))
    else
        this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out))
}