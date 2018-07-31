package mustafaozhan.github.com.cosmeticscan.old.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_manual_old.*
import mustafaozhan.github.com.cosmeticscan.R
import org.jetbrains.anko.doAsync
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


class ManualFragmentOld : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_manual_old, container, false)!!

//    private fun bindViews(view: View) {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        progressBarManual.visibility = View.GONE



        recyclerViewSearch.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)





        Observable.create(Observable.OnSubscribe<String> { subscriber ->
            eTxtSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) = Unit

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int)
                        = subscriber.onNext(s.toString())
            })
        }).debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ text ->


                    if (text.isNotEmpty()) {
                        progressBarManual.progress
                        doAsync {

//                            val ingredientList = MyDatabaseOpenHelper.getInstance(context).getMatchByName(text)
//
//                            activity!!.runOnUiThread {
//                                val adapter = ingredientList?.let { IngredientAdapter(it) }
//                                recyclerViewSearch.adapter = adapter
//                                adapter?.notifyDataSetChanged()
//                                progressBarManual.visibility = View.GONE
//
//                            }
                        }
                    }
                })


    }


}
