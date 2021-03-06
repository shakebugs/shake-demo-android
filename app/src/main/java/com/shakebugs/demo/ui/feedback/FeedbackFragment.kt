package com.shakebugs.demo.ui.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shakebugs.demo.R
import com.shakebugs.demo.databinding.FragmentFeedbackBinding
import com.shakebugs.shake.Shake
import com.shakebugs.shake.ShakeScreen

class FeedbackFragment : Fragment() {

    private var _binding: FragmentFeedbackBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        val root: View = binding.root
        context?.let { root.setBackgroundColor(it.getColor(R.color.shake_color_primary)) }

        binding.feedbackAccountLayout3.setOnClickListener {
            Shake.show(ShakeScreen.HOME)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}