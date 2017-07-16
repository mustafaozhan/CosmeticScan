package mustafaozhan.github.com.cosmeticscan.common.extensions

import mustafaozhan.github.com.cosmeticscan.common.model.Ingredients
import ninja.sakib.pultusorm.core.PultusORM
import ninja.sakib.pultusorm.core.PultusORMCondition
import ninja.sakib.pultusorm.core.PultusORMQuery

/**
Created by Mustafa Özhan on 7/12/17 at 9:34 AM on Linux.

 */
//fun PultusORM.saveInDeed(database: PultusORM, data: List<Section>) {
//
//
//    for (i in 0..data.size - 1) {
//        database.save(data[i])
//        for (j in 0..data[i].Tables!!.size - 1) {
//            database.save(data[i].Tables!![j])
//            database.save(data[i].Tables!![j].TableShape!!)
//        }
//    }
//}
//
//fun PultusORM.getTablesInSection(database: PultusORM,sectionId:Int): List<Tables> {
//
//
//
//    val condition: PultusORMCondition = PultusORMCondition.Builder()
//            .eq("SectionID", sectionId)
//            .build()
//
//   return database.find(Tables(), condition) as List<Tables>
//
//
//}
fun PultusORM.isExist(database: PultusORM, name: String): String? {
    val condition: PultusORMCondition = PultusORMCondition.Builder()
            .eq("name", name)
            .sort("name", PultusORMQuery.Sort.DESCENDING)
            .build()

    val ingredients = database.find(Ingredients(), condition)
    for (it in ingredients) {
        val ingredient = it as Ingredients
        return ingredient.name
    }
    return ""
}

fun PultusORM.checkDatabase(database: PultusORM, okunmus: CharSequence, halihazirda: String): String? {

    var temp = halihazirda
    val ingredients = database.find(Ingredients())
    for (it in ingredients) {
        val ingredient = it as Ingredients

        if (okunmus.contains(ingredient.name.toString()))
            temp = halihazirda + "\n" + ingredient.name.toString()


    }



    return temp
}