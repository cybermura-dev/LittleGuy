package ru.takeshiko.littleguy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.data.WorkoutSection

/**
 * Adapter for displaying workout sections and items in a RecyclerView.
 * The adapter supports multiple types of views (headers and items).
 * Each section contains a header and a list of workout items.
 *
 * @property workoutSections The list of WorkoutSection objects representing different workout categories.
 * @property onItemClick A lambda function to handle item clicks.
 * @constructor Creates an instance of WorkoutAdapter.
 */
class WorkoutAdapter(private val workoutSections: List<WorkoutSection>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val typeHeader = 0
    private val typeItem = 1

    /**
     * Creates a new ViewHolder for the specified view type.
     *
     * @param parent The parent view group that will contain the new view.
     * @param viewType The type of view to create (header or item).
     * @return A new instance of the corresponding ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            typeHeader -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_workout_header, parent, false)
                HeaderViewHolder(view)
            }
            typeItem -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_workout, parent, false)
                ItemViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    /**
     * Binds the data from the given position in the data set to the ViewHolder.
     *
     * @param holder The ViewHolder that will bind the data.
     * @param position The position in the data set.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val section = getSectionForPosition(position)
        val sectionPosition = getPositionForSection(section)

        when (holder) {
            is HeaderViewHolder -> {
                holder.title.text = section.title
            }
            is ItemViewHolder -> {
                val itemPosition = position - sectionPosition - 1
                if (itemPosition < 0 || itemPosition >= section.items.size) {
                    return
                }
                val item = section.items[itemPosition]
                holder.title.text = item.title
                holder.image.setImageResource(item.imageRes)
                holder.itemView.setOnClickListener {
                    onItemClick(item.link)
                }
            }
        }
    }

    /**
     * Returns the total number of items (headers + items) in the data set.
     *
     * @return The total number of items.
     */
    override fun getItemCount(): Int {
        var count = 0
        workoutSections.forEach { section ->
            count += section.items.size + 1
        }
        return count
    }

    /**
     * Returns the type of view for the given position.
     *
     * @param position The position in the data set.
     * @return The type of view for the position (header or item).
     */
    override fun getItemViewType(position: Int): Int {
        val section = getSectionForPosition(position)
        return if (position == getPositionForSection(section)) typeHeader else typeItem
    }

    /**
     * Finds the section that corresponds to the given position.
     *
     * @param position The position in the data set.
     * @return The section corresponding to the position.
     */
    private fun getSectionForPosition(position: Int): WorkoutSection {
        var cumulativePosition = 0
        for (section in workoutSections) {
            val totalItemsInSection = 1 + section.items.size
            if (position < cumulativePosition + totalItemsInSection) {
                return section
            }
            cumulativePosition += totalItemsInSection
        }
        throw IllegalStateException("Position out of bounds")
    }

    /**
     * Finds the position of the first item in the given section.
     *
     * @param section The section to find the position for.
     * @return The position of the first item in the section.
     */
    private fun getPositionForSection(section: WorkoutSection): Int {
        var cumulativePosition = 0
        for (sec in workoutSections) {
            if (sec == section) return cumulativePosition
            cumulativePosition += 1 + sec.items.size
        }
        return 0
    }

    /**
     * ViewHolder for a section header.
     *
     * @param itemView The view representing the header of a section.
     */
    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_workout_header)
    }

    /**
     * ViewHolder for a workout item.
     *
     * @param itemView The view representing a single workout item.
     */
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_workout_title)
        val image: ImageView = itemView.findViewById(R.id.iv_workout)
    }
}
