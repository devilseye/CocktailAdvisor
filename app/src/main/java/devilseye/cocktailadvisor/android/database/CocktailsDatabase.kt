package devilseye.cocktailadvisor.android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import devilseye.cocktailadvisor.android.dao.IngredientCategoryDao
import devilseye.cocktailadvisor.android.model.IngredientCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Database(entities = [IngredientCategory::class], version = 1, exportSchema = true)
abstract class CocktailsDatabase : RoomDatabase() {
    abstract fun ingredientCategoryDao(): IngredientCategoryDao

    companion object {
        @Volatile
        private var instance: CocktailsDatabase? = null

        fun getInstance(context: Context): CocktailsDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context.applicationContext).also { instance = it }
            }

        private fun buildDatabase(context: Context): CocktailsDatabase {
            val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
            return Room.databaseBuilder(context, CocktailsDatabase::class.java, "Cocktails.db")
                .addCallback(DatabaseSeeder(applicationScope))
                .build()
        }

        private class DatabaseSeeder(private val scope: CoroutineScope) : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                instance?.let { database ->
                    scope.launch {
                        if (database.ingredientCategoryDao().countCategories() == 0) {
                            database.ingredientCategoryDao().insertIngredientCategories(PREPOPULATE_DATA)
                        }
                    }
                }
            }
        }

        private val PREPOPULATE_DATA =
            listOf(
                IngredientCategory(name = "Крепкий алкоголь"),
                IngredientCategory(name = "Слабый алкоголь"),
                IngredientCategory(name = "Соки"),
                IngredientCategory(name = "Напитки"),
                IngredientCategory(name = "Фрукты"),
                IngredientCategory(name = "Разное"),
            )
    }
}
