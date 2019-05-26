package devilseye.cocktailadvisor.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import devilseye.cocktailadvisor.model.IngredientCategory

@Dao
interface IngredientCategoryDao {

    @Insert
    fun insertIngredientCategory(ingredientCategory: IngredientCategory)

    @Insert
    fun insertIngredientCategories(ingredientCategory: List<IngredientCategory>)

    @Query("SELECT * FROM ingredient_category")
    fun getIngredientCategory(): List<IngredientCategory>
}