package com.shakebugs.demo.ui.components

import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.shakebugs.demo.R

class MainAdapter(private val stepsList: ArrayList<Steps>) : RecyclerView.Adapter<MainAdapter.StepsViewHolder>() {

    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_step, parent, false)
        return StepsViewHolder(itemView, mListener)
    }


    override fun getItemCount(): Int {
        return stepsList.size
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        val currentItem = stepsList[position]
        var number = ContextCompat.getDrawable(holder.number.context, currentItem.icon)
        var circle = ContextCompat.getDrawable(holder.number.context, R.drawable.shake_circle_bg)
        if (number != null && circle != null) {
            circle = DrawableCompat.wrap(circle)
            DrawableCompat.setTint(circle, currentItem.color)
            number = DrawableCompat.wrap(number)
            DrawableCompat.setTint(number, currentItem.color)
        }
        holder.number.setImageResource(currentItem.icon)
        holder.circle.setImageResource(R.drawable.shake_circle_bg)
        holder.step.setText(currentItem.description)
        holder.step.movementMethod = LinkMovementMethod.getInstance()

    }

    class StepsViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {

        val number : ImageView = itemView.findViewById(R.id.number_fg)
        val circle : ImageView = itemView.findViewById(R.id.circle_bg)
        val step : TextView = itemView.findViewById(R.id.shake_step)

        init {
            step.setOnClickListener {
                listener?.onItemClick(absoluteAdapterPosition)
            }
        }

    }
}