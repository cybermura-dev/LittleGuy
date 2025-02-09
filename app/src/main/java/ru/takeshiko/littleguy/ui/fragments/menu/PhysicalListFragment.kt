package ru.takeshiko.littleguy.ui.fragments.menu

import android.content.Intent
import android.os.Bundle
import android.view.View
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.ui.GamesActivity
import ru.takeshiko.littleguy.ui.WorkoutActivity
import ru.takeshiko.littleguy.ui.animators.ViewClickAnimator
import ru.takeshiko.littleguy.ui.fragments.BaseFragment

/**
 * Fragment providing access to physical activity programs.
 * Serves as navigation hub for workout and game activities.
 */
class PhysicalListFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_physical_list

    /**
     * Configures physical activity buttons with click animations and navigation.
     * @param view Root fragment view
     * @param savedInstanceState Previously saved fragment state
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnGames = view.findViewById<View>(R.id.btn_games)
        val btnWorkout = view.findViewById<View>(R.id.btn_workout)

        // Create an animator for button click animations
        val animator = ViewClickAnimator()

        btnGames.setOnClickListener {
            animator.animate(btnGames, onAnimationEnd = {
                val intent = Intent(requireContext(), GamesActivity::class.java)
                startActivity(intent)
            })
        }

        btnWorkout.setOnClickListener {
            animator.animate(btnWorkout, onAnimationEnd = {
                val intent = Intent(requireContext(), WorkoutActivity::class.java)
                startActivity(intent)
            })
        }
    }
}