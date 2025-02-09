package ru.takeshiko.littleguy.ui.fragments.menu

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.data.AppDatabase
import ru.takeshiko.littleguy.data.User
import ru.takeshiko.littleguy.data.repo.UserRepository
import ru.takeshiko.littleguy.data.repo.WaterRepository
import ru.takeshiko.littleguy.ui.PersonalDataEditorActivity
import ru.takeshiko.littleguy.ui.SleepTrackerActivity
import ru.takeshiko.littleguy.ui.TrackersActivity
import ru.takeshiko.littleguy.ui.animators.ViewClickAnimator
import ru.takeshiko.littleguy.ui.fragments.BaseFragment
import ru.takeshiko.littleguy.viewmodels.UserViewModel
import ru.takeshiko.littleguy.viewmodels.UserViewModelFactory
import ru.takeshiko.littleguy.viewmodels.WaterViewModel
import ru.takeshiko.littleguy.viewmodels.WaterViewModelFactory

/**
 * User profile fragment displaying personal data and tracking information.
 * Manages user profile editing and provides quick access to trackers.
 */
class ProfileFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_profile

    private val userId = 1

    private val userRepository: UserRepository by lazy {
        val database = AppDatabase.getDatabase(requireContext())
        UserRepository(database.userDao())
    }

    private val userViewModel: UserViewModel by lazy {
        ViewModelProvider(requireActivity(), UserViewModelFactory(userRepository))[UserViewModel::class.java]
    }

    private val waterRepository: WaterRepository by lazy {
        val database = AppDatabase.getDatabase(requireContext())
        WaterRepository(database.waterRecordDao())
    }

    private val waterViewModel: WaterViewModel by lazy {
        ViewModelProvider(requireActivity(), WaterViewModelFactory(waterRepository))[WaterViewModel::class.java]
    }

    private lateinit var tvUserName: TextView
    private lateinit var tvUserAge: TextView
    private lateinit var tvUserWeight: TextView
    private lateinit var tvUserHeight: TextView
    private lateinit var tvWaterValue: TextView

    /**
     * Initializes profile UI components and data observers.
     * @param view Root fragment view
     * @param savedInstanceState Previously saved fragment state
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvUserName = view.findViewById(R.id.tv_user_name)

        val cardPersonalData: View = view.findViewById(R.id.card_personal_data)
        tvUserAge = cardPersonalData.findViewById(R.id.age_value)
        tvUserWeight = cardPersonalData.findViewById(R.id.weight_value)
        tvUserHeight = cardPersonalData.findViewById(R.id.height_value)
        val btnEdit = cardPersonalData.findViewById<ImageView>(R.id.btn_edit)

        val cardNightLevel: View = view.findViewById(R.id.card_night_level)
        val btnInput = cardNightLevel.findViewById<ImageView>(R.id.btn_input)

        val cardWaterLevel: View = view.findViewById(R.id.card_water_level)
        tvWaterValue = cardWaterLevel.findViewById(R.id.water_value)
        val btnWaterAdd = cardWaterLevel.findViewById<ImageView>(R.id.btn_water_add)

        val btnTrackers: ImageView = view.findViewById(R.id.btn_trackers)

        // Create an animator for button click animations
        val animator = ViewClickAnimator()

        btnEdit.setOnClickListener {
            animator.animate(btnEdit, onAnimationEnd = {
                val intent = Intent(requireContext(), PersonalDataEditorActivity::class.java)
                startActivity(intent)
            })
        }

        btnInput.setOnClickListener {
            animator.animate(btnInput, onAnimationEnd = {
                val intent = Intent(requireContext(), SleepTrackerActivity::class.java)
                startActivity(intent)
            })
        }

        waterViewModel.loadTodayWaterAmount(userId)

        waterViewModel.waterAmount.observe(viewLifecycleOwner) { amount ->
            tvWaterValue.text = getString(R.string.water_amount, amount)
        }

        btnWaterAdd.setOnClickListener {
            animator.animate(btnWaterAdd, onAnimationEnd = {
                waterViewModel.addWater(userId)
            })
        }

        btnTrackers.setOnClickListener {
            animator.animate(btnTrackers, onAnimationEnd = {
                val intent = Intent(requireContext(), TrackersActivity::class.java)
                startActivity(intent)
            })
        }

        loadUserData()
    }

    /**
     * Loads user data from repository and updates UI elements.
     * Handles null values with default placeholder text.
     */
    private fun loadUserData() {
        val defaultUser = User(
            id = 1,
            name = null,
            age = null,
            weight = null,
            height = null,
            avatar = null
        )

        userViewModel.checkOrCreateUser(userId, defaultUser) { user ->
            tvUserName.text = user.name ?: "НЕ ЗАДАНО"
            tvUserAge.text = user.age?.let { "$it лет" } ?: "НЕ ЗАДАНО"
            tvUserWeight.text = user.weight?.let { "$it кг" } ?: "НЕ ЗАДАНО"
            tvUserHeight.text = user.height?.let { "$it см" } ?: "НЕ ЗАДАНО"
        }
    }

    /**
     * Refreshes data when fragment becomes visible.
     */
    override fun onResume() {
        super.onResume()
        loadUserData()
        waterViewModel.loadTodayWaterAmount(userId)
    }
}