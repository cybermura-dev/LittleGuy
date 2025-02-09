package ru.takeshiko.littleguy.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.adapters.WorkoutAdapter
import ru.takeshiko.littleguy.data.WorkoutCategory
import ru.takeshiko.littleguy.data.WorkoutItem
import ru.takeshiko.littleguy.data.WorkoutSection

/**
 * Activity for displaying and organizing workout programs in categorized sections.
 * Handles workout data organization and provides navigation to external video content.
 */
class WorkoutActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var workoutAdapter: WorkoutAdapter
    private var allWorkouts = listOf<WorkoutSection>()

    /**
     * Initializes activity layout and configures RecyclerView with workout data.
     * @param savedInstanceState Previously saved activity state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        allWorkouts = getSortedWorkouts()
        workoutAdapter = WorkoutAdapter(allWorkouts) { link ->
            openUrl(link)
        }
        recyclerView.adapter = workoutAdapter
    }

    /**
     * Organizes raw workout data into categorized sections.
     * @return List of categorized workout sections
     */
    private fun getSortedWorkouts(): List<WorkoutSection> {
        val general = mutableListOf<WorkoutItem>()
        val girls = mutableListOf<WorkoutItem>()
        val boys = mutableListOf<WorkoutItem>()

        getWorkouts().forEach {
            when (it.category) {
                WorkoutCategory.GENERAL -> general.add(it)
                WorkoutCategory.GIRLS -> girls.add(it)
                WorkoutCategory.BOYS -> boys.add(it)
            }
        }

        return listOf(
            WorkoutSection("ОБЩЕЕ", general),
            WorkoutSection("МАЛЬЧИКИ", boys),
            WorkoutSection("ДЕВОЧКИ", girls)
        )
    }

    /**
     * Provides complete list of available workout programs.
     * @return List of workout items with metadata
     */
    private fun getWorkouts(): List<WorkoutItem> {
        return listOf(
            WorkoutItem("ЗАРЯДКА", R.drawable.preview_charging, WorkoutCategory.GENERAL, "https://youtube.com"),
            WorkoutItem("ГИМНАСТИКА", R.drawable.preview_gymnastics, WorkoutCategory.GENERAL, "https://youtube.com"),
            WorkoutItem("ФИЗКУЛЬТУРА", R.drawable.preview_physical_education, WorkoutCategory.GENERAL, "https://youtube.com"),
            WorkoutItem("ТРЕНИРОВКА", R.drawable.preview_general_physical_training, WorkoutCategory.GENERAL, "https://youtube.com"),
            WorkoutItem("УПРАЖНЕНИЯ ДЛЯ ОСАНКИ", R.drawable.preview_posture_exercises, WorkoutCategory.GENERAL, "https://youtube.com"),
            WorkoutItem("ОТЖИМАНИЯ ДЛЯ МАЛЬЧИКОВ", R.drawable.preview_push_ups, WorkoutCategory.BOYS, "https://youtube.com"),
            WorkoutItem("ВИДЫ ОТЖИМАНИЙ", R.drawable.preview_push_up_views, WorkoutCategory.BOYS, "https://youtube.com"),
            WorkoutItem("КАК ПРАВИЛЬНО ТРЕНИРОВАТЬСЯ", R.drawable.preview_how_to_train, WorkoutCategory.BOYS, "https://youtube.com"),
            WorkoutItem("ГАНТЕЛИ", R.drawable.preview_dumbbells, WorkoutCategory.BOYS, "https://youtube.com"),
            WorkoutItem("УПРАЖНЕНИЯ ДЛЯ ПОХУДЕНИЯ", R.drawable.preview_slimming_exercises, WorkoutCategory.BOYS, "https://youtube.com"),
            WorkoutItem("ТАНЦЕВАЛЬНАЯ РАЗМИНКА", R.drawable.preview_dance_warm_up, WorkoutCategory.GIRLS, "https://youtube.com"),
            WorkoutItem("ПОПЕРЕЧНЫЙ ШПАГАТ", R.drawable.preview_cross_twine, WorkoutCategory.GIRLS, "https://youtube.com"),
            WorkoutItem("ПРОДОЛЬНЫЙ ШПАГАТ", R.drawable.preview_length_wise_twine, WorkoutCategory.GIRLS, "https://youtube.com"),
            WorkoutItem("ОТЖИМАНИЯ ДЛЯ ДЕВОЧЕК", R.drawable.preview_woman_push_ups, WorkoutCategory.GIRLS, "https://youtube.com"),
            WorkoutItem("МОСТИК", R.drawable.preview_bridge, WorkoutCategory.GIRLS, "https://youtube.com"),
            WorkoutItem("ТРЕНИРОВКА НОГ", R.drawable.preview_legs, WorkoutCategory.GIRLS, "https://youtube.com"),
            WorkoutItem("ПРЕСС", R.drawable.preview_abs, WorkoutCategory.GIRLS, "https://youtube.com"),
        )
    }

    /**
     * Handles external URL navigation for workout video content.
     * @param url YouTube video URL to open
     */
    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
