package ru.takeshiko.littleguy.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.ui.fragments.menu.ArticleListFragment
import ru.takeshiko.littleguy.ui.fragments.menu.HomeFragment
import ru.takeshiko.littleguy.ui.fragments.menu.PhysicalListFragment
import ru.takeshiko.littleguy.ui.fragments.menu.ProfileFragment

/**
 * Main menu activity with bottom navigation controlling fragment display.
 * Manages fragment transactions and navigation button states.
 */
class MenuActivity : AppCompatActivity() {
    private lateinit var btnHome: ImageView
    private lateinit var btnActivity: ImageView
    private lateinit var btnArticle: ImageView
    private lateinit var btnProfile: ImageView

    private var activeButton: ImageView? = null

    /**
     * Configures navigation buttons and initial fragment display.
     * @param savedInstanceState Previously saved state bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btnHome = findViewById(R.id.btn_home)
        btnActivity = findViewById(R.id.btn_activity)
        btnArticle = findViewById(R.id.btn_article)
        btnProfile = findViewById(R.id.btn_profile)

        btnHome.setOnClickListener { openFragment(HomeFragment(), btnHome) }
        btnActivity.setOnClickListener { openFragment(PhysicalListFragment(), btnActivity) }
        btnArticle.setOnClickListener { openFragment(ArticleListFragment(), btnArticle) }
        btnProfile.setOnClickListener { openFragment(ProfileFragment(), btnProfile) }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }
    }

    /**
     * Handles fragment replacement and navigation button color updates.
     * @param fragment Fragment to display
     * @param clickedButton Activated navigation button
     */
    private fun openFragment(fragment: Fragment, clickedButton: ImageView) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        activeButton?.setColorFilter(ContextCompat.getColor(this, R.color.nav_icon_color))

        clickedButton.setColorFilter(ContextCompat.getColor(this, R.color.selected_nav_color))
        activeButton = clickedButton
    }
}