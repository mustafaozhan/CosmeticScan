package mustafaozhan.github.com.cosmeticscan.base.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */

@Entity(tableName = "ingredient")
data class Ingredient(
        @PrimaryKey @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "information") val information: String,
        @ColumnInfo(name = "category") val category: String,
        @ColumnInfo(name = "rating") val rating: String) :Serializable