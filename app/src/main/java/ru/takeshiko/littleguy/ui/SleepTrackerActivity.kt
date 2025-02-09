package ru.takeshiko.littleguy.ui

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.data.AppDatabase
import ru.takeshiko.littleguy.data.repo.SleepRepository
import ru.takeshiko.littleguy.viewmodels.SleepViewModel
import ru.takeshiko.littleguy.viewmodels.SleepViewModelFactory

/**
 * Sleep tracking activity with interactive SeekBar for hours input.
 * Integrates with ViewModel for data persistence and LiveData observation.
 */
class SleepTrackerActivity : AppCompatActivity() {
    private val userId = 1

    private val sleepRepository: SleepRepository by lazy {
        val database = AppDatabase.getDatabase(this)
        SleepRepository(database.sleepRecordDao())
    }

    private val sleepViewModel: SleepViewModel by lazy {
        ViewModelProvider(this, SleepViewModelFactory(sleepRepository))[SleepViewModel::class.java]
    }

    /**
     * Initializes UI components and sets up SeekBar change listener.
     * Observes sleep data changes and handles user input validation.
     * @param savedInstanceState Previously saved state bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep_tracker)

        val seekBar = findViewById<SeekBar>(R.id.sleep_seek_bar)
        val tvSleepHours = findViewById<TextView>(R.id.tv_sleep_hours)

        sleepViewModel.loadTodaySleepHours(userId)

        sleepViewModel.todaySleepHours.observe(this) { sleepHours ->
            val hours = sleepHours ?: 0
            seekBar.progress = hours
            tvSleepHours.text = getString(R.string.sleep_hours, hours)
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvSleepHours.text = getString(R.string.sleep_hours, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                val progress = seekBar?.progress ?: 0
                sleepViewModel.addOrUpdateSleepRecord(userId, progress)
            }
        })
    }
}