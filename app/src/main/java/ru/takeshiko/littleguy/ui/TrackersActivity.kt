package ru.takeshiko.littleguy.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.adapters.SleepRecordAdapter
import ru.takeshiko.littleguy.adapters.WaterRecordAdapter
import ru.takeshiko.littleguy.data.AppDatabase
import ru.takeshiko.littleguy.data.repo.SleepRepository
import ru.takeshiko.littleguy.data.repo.WaterRepository
import ru.takeshiko.littleguy.ui.animators.ViewClickAnimator
import ru.takeshiko.littleguy.viewmodels.SleepViewModel
import ru.takeshiko.littleguy.viewmodels.SleepViewModelFactory
import ru.takeshiko.littleguy.viewmodels.WaterViewModel
import ru.takeshiko.littleguy.viewmodels.WaterViewModelFactory

/**
 * Activity combining sleep and water tracking functionality.
 * Manages dual tracking systems with historical data visualization.
 */
class TrackersActivity : AppCompatActivity() {
    private lateinit var sleepRecordAdapter: SleepRecordAdapter
    private lateinit var waterRecordAdapter: WaterRecordAdapter

    private val userId = 1

    private val sleepRepository: SleepRepository by lazy {
        val database = AppDatabase.getDatabase(this)
        SleepRepository(database.sleepRecordDao())
    }

    private val sleepViewModel: SleepViewModel by lazy {
        ViewModelProvider(this, SleepViewModelFactory(sleepRepository))[SleepViewModel::class.java]
    }

    private val waterRepository: WaterRepository by lazy {
        val database = AppDatabase.getDatabase(this)
        WaterRepository(database.waterRecordDao())
    }

    private val waterViewModel: WaterViewModel by lazy {
        ViewModelProvider(this, WaterViewModelFactory(waterRepository))[WaterViewModel::class.java]
    }

    /**
     * Initializes dual tracking systems and configures UI components.
     * @param savedInstanceState Previously saved activity state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trackers)

        setupSleepTracking()
        setupWaterTracking()
    }

    /**
     * Configures sleep tracking components including:
     * - RecyclerView for historical data
     * - ViewModel integration
     * - Navigation to detailed sleep tracking
     */
    private fun setupSleepTracking() {
        val cardSleepList: View = findViewById(R.id.card_sleep_list)
        val cardSleepRecord: View = findViewById(R.id.card_night_level)
        val btnInput: ImageView = cardSleepRecord.findViewById(R.id.btn_input)

        val sleepRecyclerView: RecyclerView = cardSleepList.findViewById(R.id.rv_sleep_records)
        sleepRecyclerView.layoutManager = LinearLayoutManager(this)
        sleepRecyclerView.isNestedScrollingEnabled = false

        sleepRecordAdapter = SleepRecordAdapter()
        sleepRecyclerView.adapter = sleepRecordAdapter

        sleepViewModel.loadTodaySleepHours(userId)
        sleepViewModel.getUserSleepRecords(userId).observe(this) { records ->
            sleepRecordAdapter.submitList(records)
        }

        btnInput.setOnClickListener {
            ViewClickAnimator().animate(btnInput) {
                startActivity(Intent(this, SleepTrackerActivity::class.java))
            }
        }
    }

    /**
     * Configures water intake tracking components including:
     * - Real-time updates display
     * - Water consumption recording
     * - Historical data visualization
     */
    private fun setupWaterTracking() {
        val cardWaterList: View = findViewById(R.id.card_water_list)
        val cardWaterRecord: View = findViewById(R.id.card_water_level)
        val btnWaterAdd: ImageView = cardWaterRecord.findViewById(R.id.btn_water_add)
        val tvWaterValue: TextView = cardWaterRecord.findViewById(R.id.water_value)

        val waterRecyclerView: RecyclerView = cardWaterList.findViewById(R.id.rv_water_records)
        waterRecyclerView.layoutManager = LinearLayoutManager(this)
        waterRecyclerView.isNestedScrollingEnabled = false

        waterRecordAdapter = WaterRecordAdapter()
        waterRecyclerView.adapter = waterRecordAdapter

        waterViewModel.loadTodayWaterAmount(userId)
        waterViewModel.getUserWaterRecords(userId).observe(this) { records ->
            waterRecordAdapter.submitList(records)
        }

        waterViewModel.waterAmount.observe(this) { amount ->
            tvWaterValue.text = getString(R.string.water_amount, amount)
        }

        btnWaterAdd.setOnClickListener {
            ViewClickAnimator().animate(btnWaterAdd) {
                waterViewModel.addWater(userId)
            }
        }
    }

    /**
     * Refreshes tracking data when activity resumes.
     */
    override fun onResume() {
        super.onResume()
        sleepViewModel.loadTodaySleepHours(userId)
    }
}
