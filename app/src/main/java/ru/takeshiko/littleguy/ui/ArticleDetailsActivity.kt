package ru.takeshiko.littleguy.ui

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.takeshiko.littleguy.R

/**
 * Activity for displaying the details of a selected article.
 * Retrieves title and description from Intent and displays them.
 */
class ArticleDetailsActivity : AppCompatActivity() {
    /**
     * Initializes the activity layout, retrieves article data from Intent, and sets the text in UI.
     * @param savedInstanceState Previously saved state bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        val cardView: View = findViewById(R.id.cv_description)
        val articleTitle: TextView = findViewById(R.id.tv_title)
        val articleDescription: TextView = cardView.findViewById(R.id.tv_desc)

        val title = intent.getStringExtra("TITLE")
        val description = intent.getStringExtra("DESCRIPTION")

        articleTitle.text = title
        articleDescription.text = Html.fromHtml(
            description,
            Html.FROM_HTML_MODE_COMPACT
        )
        articleDescription.movementMethod = LinkMovementMethod.getInstance()
    }
}