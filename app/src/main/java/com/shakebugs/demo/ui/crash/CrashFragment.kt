package com.shakebugs.demo.ui.crash

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shakebugs.demo.R
import com.shakebugs.demo.databinding.FragmentCrashBinding
import com.shakebugs.demo.ui.components.MainAdapter
import com.shakebugs.demo.ui.components.Steps
import java.lang.ArithmeticException

class CrashFragment : Fragment() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Steps>
    private var _binding: FragmentCrashBinding? = null
    private lateinit var iconId: Array<Int>
    private lateinit var description: Array<Int>

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCrashBinding.inflate(inflater, container, false)
        val root: View = binding.root
        context?.let { root.setBackgroundColor(it.getColor(R.color.shake_color_primary)) }

        iconId = arrayOf(
            R.drawable.ic_one,
            R.drawable.ic_two
        )
        description = arrayOf(
            R.string.crash_try_this_1,
            R.string.crash_try_this_2
        )

        newRecyclerView = binding.crashRecyclerView
        newRecyclerView.layoutManager = LinearLayoutManager(this.activity)
        newRecyclerView.setHasFixedSize(true)
        newRecyclerView.isNestedScrollingEnabled = false

        newArrayList = arrayListOf()
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

        val adapter = MainAdapter(newArrayList)
        newRecyclerView.adapter = adapter
        adapter.setOnClickListener(object : MainAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                if (position == 0) {
                    throw ArithmeticException("App crashed!")
                }
            }
        })
    }
}