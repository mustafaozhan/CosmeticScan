package mustafaozhan.github.com.cosmeticscan.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.common.model.Ingredients
import mustafaozhan.github.com.cosmeticscan.ui.adapters.IngredientAdapter

class IngredientsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        val extras = intent.extras
        if (extras != null) {
            // data = extras.getString("data")
        }

        //getting recyclerview from xml
        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


        //crating an arraylist to store users using the data class user
        val ingredient = ArrayList<Ingredients>()

       // val c: data


        //adding some dummy data to the list

     //   ingredient.add(Ingredients("Ingredients"))


        //creating our adapter
        val adapter = IngredientAdapter(ingredient)

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter
    }


}
