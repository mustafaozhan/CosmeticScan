package mustafaozhan.github.com.cosmeticscan.ui.activities

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_ingredients.*
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.common.model.database
import mustafaozhan.github.com.cosmeticscan.ui.adapters.IngredientAdapter


class IngredientsActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        val extras = intent.extras

        var data = extras.getString("data")



        data = data.substring(0, data.length - 1)

        val names = data.split(",")
        val ingredientList = database.getIngredientsByName(names)



        recyclerViewIngredients.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val adapter = IngredientAdapter(ingredientList)

        recyclerViewIngredients.adapter = adapter
    }


}
