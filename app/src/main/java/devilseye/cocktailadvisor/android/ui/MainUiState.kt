package devilseye.cocktailadvisor.android.ui

import devilseye.cocktailadvisor.android.model.IngredientCategory

data class MainUiState(
    val categories: List<IngredientCategory> = emptyList(),
)
