package devilseye.cocktailadvisor.android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import devilseye.cocktailadvisor.android.model.IngredientCategory

const val NOT_IMPLEMENTED = "not implemented"

class IngredientCategoryAdapter(
    private val context: Context,
    private val dataSource: ArrayList<IngredientCategory>
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO(NOT_IMPLEMENTED) //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(position: Int): Any {
        TODO(NOT_IMPLEMENTED) //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(position: Int): Long {
        TODO(NOT_IMPLEMENTED) //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO(NOT_IMPLEMENTED) //To change body of created functions use File | Settings | File Templates.
    }

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
}