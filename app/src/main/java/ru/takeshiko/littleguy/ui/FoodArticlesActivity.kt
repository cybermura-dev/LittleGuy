package ru.takeshiko.littleguy.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.adapters.ArticleAdapter
import ru.takeshiko.littleguy.data.Article
import ru.takeshiko.littleguy.databinding.ActivityFoodArticlesBinding
import ru.takeshiko.littleguy.utils.SpaceItemDecoration

/**
 * Activity displaying a list of food-related articles in a RecyclerView.
 * Allows navigation to detailed article view on item click.
 */
class FoodArticlesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodArticlesBinding

    /**
     * Initializes the activity layout, creates article list and configures RecyclerView.
     * @param savedInstanceState Previously saved state bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articles = listOf(
            Article(
                title = getString(R.string.food_article_title_first),
                shortDesc = getString(R.string.food_article_short_desc_first),
                description = getString(R.string.food_article_desc_first)
            ),
            Article(
                title = getString(R.string.food_article_title_second),
                shortDesc = getString(R.string.food_article_short_desc_second),
                description = getString(R.string.food_article_desc_second)
            ),
            Article(
                title = getString(R.string.food_article_title_third),
                shortDesc = getString(R.string.food_article_short_desc_third),
                description = getString(R.string.food_article_desc_third)
            )
        )

        binding.rvFoodArticles.layoutManager = LinearLayoutManager(this)
        binding.rvFoodArticles.adapter = ArticleAdapter(articles) { article ->
            val intent = Intent(this, ArticleDetailsActivity::class.java)
            intent.putExtra("TITLE", article.title)
            intent.putExtra("DESCRIPTION", article.description)
            startActivity(intent)
        }

        val space = resources.getDimensionPixelSize(R.dimen.card_margin)
        binding.rvFoodArticles.addItemDecoration(SpaceItemDecoration(space))
    }
}