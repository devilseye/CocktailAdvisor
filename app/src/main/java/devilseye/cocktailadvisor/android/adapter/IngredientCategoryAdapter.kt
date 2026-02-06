package devilseye.cocktailadvisor.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import devilseye.cocktailadvisor.android.databinding.ItemIngredientCategoryBinding
import devilseye.cocktailadvisor.android.model.IngredientCategory

class IngredientCategoryAdapter :
    ListAdapter<IngredientCategory, IngredientCategoryAdapter.IngredientCategoryViewHolder>(
        IngredientCategoryDiffCallback,
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): IngredientCategoryViewHolder {
        val binding =
            ItemIngredientCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        return IngredientCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: IngredientCategoryViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    class IngredientCategoryViewHolder(
        private val binding: ItemIngredientCategoryBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: IngredientCategory) {
            binding.categoryName.text = item.name
        }
    }

    private object IngredientCategoryDiffCallback : DiffUtil.ItemCallback<IngredientCategory>() {
        override fun areItemsTheSame(
            oldItem: IngredientCategory,
            newItem: IngredientCategory,
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: IngredientCategory,
            newItem: IngredientCategory,
        ): Boolean = oldItem == newItem
    }
}
