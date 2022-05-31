package com.example.strap.adapter

import android.view.View
import com.example.strap.databinding.ItemCommunityRoutineBinding
import com.example.strap.entity.Routine
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.strap.R

class CommunityRoutineItemAdapter :
    RecyclerView.Adapter<CommunityRoutineItemAdapter.ViewHolder>() {
    private var communityRoutines: List<Routine> = listOf()

    inner class ViewHolder(private val binding: ItemCommunityRoutineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindViews(item: Routine) = with(binding) {
            when (position) {
                0 -> Glide.with(routineImage)
                    .load(R.drawable.recommended1)
                    .centerCrop()
                    .into(routineImage)
                1 -> Glide.with(routineImage)
                    .load(R.drawable.recommended2)
                    .centerCrop()
                    .into(routineImage)
                2 -> Glide.with(routineImage)
                    .load(R.drawable.recommended3)
                    .centerCrop()
                    .into(routineImage)
                else -> {
                    routineCardView.visibility = View.GONE
                    addCardView.visibility = View.VISIBLE

                    btnAddRoutine.setOnClickListener {
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommunityRoutineBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(communityRoutines[position])
    }

    override fun getItemCount(): Int = communityRoutines.size

    fun submitList(items: List<Routine>) {
        communityRoutines = items
        notifyDataSetChanged()
    }
}