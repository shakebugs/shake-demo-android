package com.shakebugs.demo.ui.welcome

import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shakebugs.demo.ui.components.MainAdapter
import com.shakebugs.demo.R
import com.shakebugs.demo.ui.components.Steps
import com.shakebugs.demo.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Steps>
    private var _binding: FragmentWelcomeBinding? = null
    lateinit var iconId: Array<Int>
    lateinit var description: Array<Int>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        iconId = arrayOf(
            R.drawable.ic_one,
            R.drawable.ic_two
        )
        description = arrayOf(
            R.string.welcome_try_this_1,
            R.string.welcome_try_this_2
        )

        newRecyclerView = binding.welcomeRecyclerView
        newRecyclerView.layoutManager = LinearLayoutManager(this.activity)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Steps>()
        getSteps()

        val learnMoreText: TextView = binding.welcomeLearnMore
        learnMoreText.setMovementMethod(LinkMovementMethod.getInstance())
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getSteps() {
        for (i in iconId.indices) {
            val step = Steps(iconId[i], description[i], Color.parseColor("#4cd266"))
            newArrayList.add(step)
        }

        newRecyclerView.adapter = MainAdapter(newArrayList)
    }


}