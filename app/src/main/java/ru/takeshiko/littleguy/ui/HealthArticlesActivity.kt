package ru.takeshiko.littleguy.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.adapters.ArticleAdapter
import ru.takeshiko.littleguy.data.Article
import ru.takeshiko.littleguy.databinding.ActivityHealthArticlesBinding
import ru.takeshiko.littleguy.utils.SpaceItemDecoration

/**
 * Activity displaying health-related articles in a scrollable list.
 * Implements click handling for article details navigation.
 */
class HealthArticlesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHealthArticlesBinding

    /**
     * Sets up activity layout and initializes RecyclerView with health articles.
     * @param savedInstanceState Previously saved state bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHealthArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articles = listOf(
            Article(
                title = getString(R.string.health_article_title_first),
                shortDesc = getString(R.string.health_article_short_desc_first),
                description = getString(R.string.health_article_desc_first)
            ),
            Article(
                title = getString(R.string.health_article_title_second),
                shortDesc = getString(R.string.health_article_short_desc_second),
                description = getString(R.string.health_article_desc_second)
            ),
            Article(
                title = getString(R.string.health_article_title_third),
                shortDesc = getString(R.string.health_article_short_desc_third),
                description = getString(R.string.health_article_desc_third)
            )
        )

        binding.rvHealthArticles.layoutManager = LinearLayoutManager(this)
        binding.rvHealthArticles.adapter = ArticleAdapter(articles) { article ->
            val intent = Intent(this, ArticleDetailsActivity::class.java)
            intent.putExtra("TITLE", article.title)
            intent.putExtra("DESCRIPTION", article.description)
            startActivity(intent)
        }

        val space = resources.getDimensionPixelSize(R.dimen.card_margin)
        binding.rvHealthArticles.addItemDecoration(SpaceItemDecoration(space))
    }
}