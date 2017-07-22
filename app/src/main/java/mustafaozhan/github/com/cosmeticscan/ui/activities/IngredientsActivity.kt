package mustafaozhan.github.com.cosmeticscan.ui.activities

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
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


        //getting recyclerview from xml
        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        data = data.substring(0, data.length - 1)
        //adding a layoutmanager

        val names = data.split(",")
        val ingredientList=database.getIngredientsByName(names)



        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

//        val parser = rowParser {
//            id: Int, name: String, information: String, category: String, rating: String ->
//            Ingredient(id, name, information, category, rating)
//        }
//
//val dataList=ArrayList<String>(Arrays.asList(data.split(",").toString()))
//
//        for (i in 0..dataList.size-1) {
//           val ingredientList=database.readableDatabase.select("Ingredients")
//                    .whereArgs("(name = {userName})",
//                            "userName" to dataList[i]).exec {  parseList(parser)  }
//
//Log.d("asdas",ingredientList[i].name)
//        }
        //crating an arraylist to store users using the data class user
        //  val ingredient = database.getByData(data)

        // val c: data


        //adding some dummy data to the list

        //   ingredient.add(Ingredient("Ingredient"))


        //creating our adapter
        val adapter = IngredientAdapter(ingredientList)

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter
    }


}
