package ru.takeshiko.littleguy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Base class for all application fragments.
 * Provides common fragment infrastructure and layout handling.
 */
abstract class BaseFragment : Fragment() {
    /**
     * Resource ID for fragment layout
     */
    abstract val layoutRes: Int

    /**
     * Inflates fragment layout using provided resource ID.
     * @return Inflated view hierarchy
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutRes, container, false)
    }

    open fun setupViews(view: View) {}
}