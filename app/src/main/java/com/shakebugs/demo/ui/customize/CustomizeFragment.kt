package com.shakebugs.demo.ui.customize

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shakebugs.demo.R
import com.shakebugs.demo.databinding.FragmentCustomizeBinding
import com.shakebugs.demo.ui.components.MainAdapter
import com.shakebugs.demo.ui.components.Steps

class CustomizeFragment : Fragment() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Steps>
    private var _binding: FragmentCustomizeBinding? = null
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
        _binding = FragmentCustomizeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        iconId = arrayOf(
            R.drawable.ic_one,
            R.drawable.ic_two
        )
        description = arrayOf(
            R.string.customize_try_this_1,
            R.string.customize_try_this_2
        )

        newRecyclerView = binding.customizeRecyclerView
        newRecyclerView.layoutManager = LinearLayoutManager(this.activity)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Steps>()
        getSteps()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getSteps() {
        for (i in iconId.indices) {
            val step = Steps(iconId[i], description[i], Color.parseColor("#d75ed7"))
            newArrayList.add(step)
        }

        newRecyclerView.adapter = MainAdapter(newArrayList)
    }
}