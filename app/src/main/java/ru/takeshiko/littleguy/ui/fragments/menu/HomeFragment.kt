package ru.takeshiko.littleguy.ui.fragments.menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.ui.animators.ViewClickAnimator
import ru.takeshiko.littleguy.ui.fragments.BaseFragment

/**
 * Home screen fragment with social group quick-access buttons.
 * Provides direct links to external social media communities.
 */
class HomeFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_home

    /**
     * Sets up social group buttons with click animations and URL handling.
     * @param view Root fragment view
     * @param savedInstanceState Previously saved fragment state
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupFirst: ImageView = view.findViewById(R.id.first_group)
        val groupSecond: ImageView = view.findViewById(R.id.second_group)
        val groupThird: ImageView = view.findViewById(R.id.third_group)

        val animator = ViewClickAnimator()

        groupFirst.setOnClickListener {
            animator.animate(groupFirst, onAnimationEnd = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/club222989671"))
                startActivity(intent)
            })
        }

        groupSecond.setOnClickListener {
            animator.animate(groupSecond, onAnimationEnd = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/club41817979"))
                startActivity(intent)
            })
        }

        groupThird.setOnClickListener {
            animator.animate(groupThird, onAnimationEnd = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/club214524833"))
                startActivity(intent)
            })
        }
    }
}