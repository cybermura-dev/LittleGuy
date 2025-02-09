package ru.takeshiko.littleguy.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.adapters.ArticleAdapter
import ru.takeshiko.littleguy.data.Article
import ru.takeshiko.littleguy.databinding.ActivityPromotionArticlesBinding
import ru.takeshiko.littleguy.utils.SpaceItemDecoration

/**
 * Activity displaying promotional articles in a vertical list.
 * Provides item click navigation to detailed article view.
 */
class PromotionArticlesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPromotionArticlesBinding

    /**
     * Initializes view binding and sets up RecyclerView with promotional content.
     * @param savedInstanceState Previously saved state bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPromotionArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articles = listOf(
            Article(
                title = getString(R.string.promotion_article_title_first),
                shortDesc = getString(R.string.promotion_article_short_desc_first),
                description = getString(R.string.promotion_article_desc_first)
            ),
            Article(
                title = getString(R.string.promotion_article_title_second),
                shortDesc = getString(R.string.promotion_article_short_desc_second),
                description = getString(R.string.promotion_article_desc_second)
            ),
            Article(
                title = getString(R.string.promotion_article_title_third),
                shortDesc = getString(R.string.promotion_article_short_desc_third),
                description = getString(R.string.promotion_article_desc_third)
            )
        )

        binding.rvPromotionArticles.layoutManager = LinearLayoutManager(this)
        binding.rvPromotionArticles.adapter = ArticleAdapter(articles) { article ->
            val intent = Intent(this, ArticleDetailsActivity::class.java)
            intent.putExtra("TITLE", article.title)
            intent.putExtra("DESCRIPTION", article.description)
            startActivity(intent)
        }

        val space = resources.getDimensionPixelSize(R.dimen.card_margin)
        binding.rvPromotionArticles.addItemDecoration(SpaceItemDecoration(space))
    }
}