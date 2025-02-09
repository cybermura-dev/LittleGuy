package ru.takeshiko.littleguy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.data.WaterRecord
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Adapter for displaying water consumption records in a RecyclerView.
 * It uses a ListAdapter with DiffUtil to efficiently handle the updates.
 * Each item shows the amount of water consumed and the time of consumption.
 *
 * @property waterRecords The list of WaterRecord objects to be displayed in the adapter.
 * @constructor Creates an instance of WaterRecordAdapter.
 */
class WaterRecordAdapter : ListAdapter<WaterRecord, WaterRecordAdapter.WaterViewHolder>(DiffCallback()) {

    /**
     * ViewHolder that holds the views for displaying a single water record item.
     * Binds the data of WaterRecord to the UI components.
     *
     * @param itemView The view representing a single item in the list.
     */
    class WaterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvAmount: TextView = itemView.findViewById(R.id.tv_amount)
        private val tvTime: TextView = itemView.findViewById(R.id.tv_time)

        /**
         * Binds the data of a WaterRecord to the views.
         *
         * @param record The WaterRecord object containing data to be displayed.
         */
        fun bind(record: WaterRecord) {
            tvAmount.text = "${record.amount} мл"

            // Parse and format the time for display
            val time = LocalTime.parse(record.dateTime.split(" ")[1])
            val formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm"))
            tvTime.text = formattedTime
        }
    }

    /**
     * Creates a new ViewHolder for a water record item view.
     *
     * @param parent The parent view group that will contain the new view.
     * @param viewType The type of view to create.
     * @return A new instance of WaterViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_water_record, parent, false)
        return WaterViewHolder(view)
    }

    /**
     * Binds the data from the given position in the data set to the ViewHolder.
     *
     * @param holder The ViewHolder that will bind the data.
     * @param position The position in the data set.
     */
    override fun onBindViewHolder(holder: WaterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * Callback for calculating item differences using DiffUtil.
     *
     * @constructor Creates a new DiffCallback.
     */
    private class DiffCallback : DiffUtil.ItemCallback<WaterRecord>() {
        /**
         * Checks whether two items represent the same object.
         *
         * @param oldItem The previous item.
         * @param newItem The new item.
         * @return `true` if both items represent the same object.
         */
        override fun areItemsTheSame(oldItem: WaterRecord, newItem: WaterRecord) = oldItem.id == newItem.id

        /**
         * Checks whether the contents of two items are the same.
         *
         * @param oldItem The previous item.
         * @param newItem The new item.
         * @return `true` if the contents of both items are the same.
         */
        override fun areContentsTheSame(oldItem: WaterRecord, newItem: WaterRecord) = oldItem == newItem
    }
}
