package devilseye.cocktailadvisor.android.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import devilseye.cocktailadvisor.android.dao.IngredientCategoryDao
import devilseye.cocktailadvisor.android.model.IngredientCategory
import devilseye.cocktailadvisor.android.util.ioThread

@Database(entities = [IngredientCategory::class], version = 1)
abstract class CocktailsDatabase : RoomDatabase() {

    abstract fun ingredientCategoryDao(): IngredientCategoryDao

    companion object {

        @Volatile
        private var INSTANCE: CocktailsDatabase? = null

        fun getInstance(context: Context): CocktailsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CocktailsDatabase::class.java, "Cocktails.db"
            )
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        ioThread {
                            getInstance(context).ingredientCategoryDao().insertIngredientCategories(PREPOPULATE_DATA)
                        }
                    }
                })
                .build()

        val PREPOPULATE_DATA = listOf(
            IngredientCategory(null, "Крепкий алкоголь"),
            IngredientCategory(null, "Слабый алкоголь"),
            IngredientCategory(null, "Соки"),
            IngredientCategory(null, "Напитки"),
            IngredientCategory(null, "Фрукты"),
            IngredientCategory(null, "Разное")
        )
    }
}