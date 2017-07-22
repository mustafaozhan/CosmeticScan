package mustafaozhan.github.com.cosmeticscan.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_manual.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.common.model.Ingredient
import mustafaozhan.github.com.cosmeticscan.common.model.MyDatabaseOpenHelper
import mustafaozhan.github.com.cosmeticscan.ui.adapters.IngredientAdapter
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


class ManualFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater!!.inflate(R.layout.fragment_manual, container, false)
//        bindViews(fragmentView)
        return fragmentView
    }

//    private fun bindViews(view: View) {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)






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
                .subscribe({
                    text ->

                   val ingredientList = MyDatabaseOpenHelper.getInstance(context).getMatchByName(text)
                    val adapter = ingredientList?.let { IngredientAdapter(it) }
                    recyclerViewSearch.adapter = adapter
                    adapter?.notifyDataSetChanged()



                })


    }


}
