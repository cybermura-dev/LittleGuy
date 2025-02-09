package ru.takeshiko.littleguy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.takeshiko.littleguy.data.Article
import ru.takeshiko.littleguy.databinding.ItemShortArticleBinding

/**
 * Adapter for displaying a list of articles in a RecyclerView.
 *
 * @param articles A list of articles to be displayed.
 * @param onClick A callback that is invoked when an article is clicked.
 */
class ArticleAdapter(private val articles: List<Article>, private val onClick: (Article) -> Unit) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    /**
     * Creates a new view holder for displaying an article.
     *
     * @param parent The parent view group.
     * @param viewType The view type of the item to be created.
     * @return A new ArticleViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemShortArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    /**
     * Binds the article data to the view holder.
     *
     * @param holder The view holder.
     * @param position The position of the item in the list.
     */
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
        holder.itemView.setOnClickListener { onClick(article) }
    }

    /**
     * Returns the number of articles in the list.
     *
     * @return The total number of articles.
     */
    override fun getItemCount(): Int = articles.size

    inner class ArticleViewHolder(private val binding: ItemShortArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvDesc.text = article.shortDesc
        }
    }
}
