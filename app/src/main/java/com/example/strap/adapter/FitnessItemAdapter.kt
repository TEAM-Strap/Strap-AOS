package com.example.strap.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.strap.databinding.ItemFitnessListBinding
import com.example.strap.entity.Fitness

class FitnessItemAdapter(private val itemClickListener: (Fitness) -> Unit) :
    RecyclerView.Adapter<FitnessItemAdapter.ViewHolder>() {
    private var fitnessItems: List<Fitness> = listOf()

    inner class ViewHolder(private val binding: ItemFitnessListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindViews(fitness: Fitness) = with(binding) {
            nameTextView.text = fitness.name
            phoneTextView.text = fitness.phone
            locationTextview.text = fitness.location
            communityTextView.text = (fitness.community?.size ?: 0).toString()

            Glide.with(imageView.context)
                .load(fitness.image)
                .centerCrop()
                .into(imageView)

            root.setOnClickListener {
                itemClickListener(fitness)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFitnessListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(fitnessItems[position])
    }

    override fun getItemCount() = fitnessItems.size

    fun submitList(items: List<Fitness>) {
        fitnessItems = items
        notifyDataSetChanged()
    }
}