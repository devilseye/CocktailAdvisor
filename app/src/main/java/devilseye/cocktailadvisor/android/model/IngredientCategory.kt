package devilseye.cocktailadvisor.android.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_category")
data class IngredientCategory(@PrimaryKey(autoGenerate = true) val id: Long?, val name: String)