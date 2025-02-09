package ru.takeshiko.littleguy.ui.fragments.menu

import android.content.Intent
import android.os.Bundle
import android.view.View
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.ui.FoodArticlesActivity
import ru.takeshiko.littleguy.ui.HealthArticlesActivity
import ru.takeshiko.littleguy.ui.PromotionArticlesActivity
import ru.takeshiko.littleguy.ui.animators.ViewClickAnimator
import ru.takeshiko.littleguy.ui.fragments.BaseFragment

/**
 * Fragment displaying categorized article navigation options.
 * Provides access to different article types through visual buttons.
 */
class ArticleListFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_article_list

    /**
     * Configures article category buttons with click animations and navigation.
     * @param view Root fragment view
     * @param savedInstanceState Previously saved fragment state
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnFoodArticle = view.findViewById<View>(R.id.btn_food_article)
        val btnHealthArticle = view.findViewById<View>(R.id.btn_health_article)
        val btnPromotionArticle = view.findViewById<View>(R.id.btn_promotion_article)

        // Create an animator for button click animations
        val animator = ViewClickAnimator()

        btnFoodArticle.setOnClickListener {
            animator.animate(btnFoodArticle, onAnimationEnd = {
                val intent = Intent(view.context, FoodArticlesActivity::class.java)
                startActivity(intent)
            })
        }

        btnHealthArticle.setOnClickListener {
            animator.animate(btnHealthArticle, onAnimationEnd = {
                val intent = Intent(view.context, HealthArticlesActivity::class.java)
                startActivity(intent)
            })
        }

        btnPromotionArticle.setOnClickListener {
            animator.animate(btnPromotionArticle, onAnimationEnd = {
                val intent = Intent(view.context, PromotionArticlesActivity::class.java)
                startActivity(intent)
            })
        }
    }
}