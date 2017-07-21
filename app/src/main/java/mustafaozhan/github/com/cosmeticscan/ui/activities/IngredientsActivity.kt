package mustafaozhan.github.com.cosmeticscan.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.common.model.Ingredient
import mustafaozhan.github.com.cosmeticscan.common.model.database
import mustafaozhan.github.com.cosmeticscan.ui.adapters.IngredientAdapter
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import java.util.*

class IngredientsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        val extras = intent.extras

        val data = extras.getString("data")


        //getting recyclerview from xml
        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        //adding a layoutmanager
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
       // val adapter = IngredientAdapter(ingredientList)

        //now adding the adapter to recyclerview
      //  recyclerView.adapter = adapter
    }


}
