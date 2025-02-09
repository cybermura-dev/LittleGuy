package ru.takeshiko.littleguy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.data.SleepRecord

/**
 * Adapter for displaying a list of sleep records in a RecyclerView.
 * It supports efficient updating of items using DiffUtil.
 */
class SleepRecordAdapter : ListAdapter<SleepRecord, SleepRecordAdapter.SleepViewHolder>(DiffCallback()) {

    /**
     * ViewHolder that holds references to views for displaying a sleep record.
     */
    class SleepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvHours: TextView = itemView.findViewById(R.id.tv_hours)
        private val tvDate: TextView = itemView.findViewById(R.id.tv_date)

        /**
         * Binds the sleep record data to the views.
         *
         * @param record The sleep record to bind.
         */
        fun bind(record: SleepRecord) {
            tvHours.text = "${record.hours} часов"
            tvDate.text = record.dateTime
        }
    }

    /**
     * Creates a new view holder for displaying a sleep record.
     *
     * @param parent The parent view group.
     * @param viewType The view type of the item to be created.
     * @return A new SleepViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sleep_record, parent, false)
        return SleepViewHolder(view)
    }

    /**
     * Binds the sleep record data to the view holder.
     *
     * @param holder The view holder.
     * @param position The position of the item in the list.
     */
    override fun onBindViewHolder(holder: SleepViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * DiffUtil callback for comparing sleep records.
     */
    private class DiffCallback : DiffUtil.ItemCallback<SleepRecord>() {
        override fun areItemsTheSame(oldItem: SleepRecord, newItem: SleepRecord) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: SleepRecord, newItem: SleepRecord) = oldItem == newItem
    }
}
