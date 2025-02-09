package ru.takeshiko.littleguy.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * A custom ItemDecoration for adding spacing between items in a RecyclerView.
 * It adds a specified space above and below each item.
 *
 * @param space The space (in pixels) to add between items.
 */
class SpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    /**
     * Adjusts the offset of each item in the RecyclerView to add spacing.
     *
     * @param outRect The Rect to be updated with offsets.
     * @param view The current item view.
     * @param parent The RecyclerView this item belongs to.
     * @param state The current state of the RecyclerView.
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        // Add space above and below the item
        outRect.top = space
        outRect.bottom = space
    }
}
