package devilseye.cocktailadvisor.android.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import devilseye.cocktailadvisor.android.model.IngredientCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredientCategories(categories: List<IngredientCategory>)

    @Query("SELECT * FROM ingredient_category ORDER BY name")
    fun observeIngredientCategories(): Flow<List<IngredientCategory>>

    @Query("SELECT COUNT(*) FROM ingredient_category")
    suspend fun countCategories(): Int
}
