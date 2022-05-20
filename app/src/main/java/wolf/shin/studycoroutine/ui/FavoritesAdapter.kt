package wolf.shin.studycoroutine.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wolf.shin.studycoroutine.naver_search.model.Item

class FavoritesAdapter : RecyclerView.Adapter<ImageSearchViewHolder>() {

    private var items: List<Item> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSearchViewHolder {
        return ImageSearchViewHolder.create({}, parent)
    }

    override fun onBindViewHolder(holder: ImageSearchViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

}