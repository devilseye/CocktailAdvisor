package devilseye.cocktailadvisor.android

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import devilseye.cocktailadvisor.android.adapter.IngredientCategoryAdapter
import devilseye.cocktailadvisor.android.database.CocktailsDatabase
import devilseye.cocktailadvisor.android.databinding.ActivityMainBinding
import devilseye.cocktailadvisor.android.repository.IngredientCategoryRepository
import devilseye.cocktailadvisor.android.ui.MainViewModel
import devilseye.cocktailadvisor.android.ui.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val categoryAdapter = IngredientCategoryAdapter()

    private val viewModel: MainViewModel by viewModels {
        val database = CocktailsDatabase.getInstance(applicationContext)
        val repository = IngredientCategoryRepository(database.ingredientCategoryDao())
        MainViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        observeState()
    }

    private fun setupUi() {
        binding.categoryList.adapter = categoryAdapter
        binding.navigation.setOnItemSelectedListener { true }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    categoryAdapter.submitList(state.categories)
                }
            }
        }
    }
}
