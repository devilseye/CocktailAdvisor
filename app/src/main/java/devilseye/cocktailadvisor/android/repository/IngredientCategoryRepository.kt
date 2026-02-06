package devilseye.cocktailadvisor.android.repository

import devilseye.cocktailadvisor.android.dao.IngredientCategoryDao
import devilseye.cocktailadvisor.android.model.IngredientCategory
import kotlinx.coroutines.flow.Flow

class IngredientCategoryRepository(
    private val ingredientCategoryDao: IngredientCategoryDao,
) {
    fun observeCategories(): Flow<List<IngredientCategory>> = ingredientCategoryDao.observeIngredientCategories()
}
