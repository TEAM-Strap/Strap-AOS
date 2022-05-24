package com.example.strap.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.strap.databinding.ItemBadgeBinding
import com.example.strap.entity.Badge

class BadgeAdapter(private val badges: ArrayList<Badge>) :
    RecyclerView.Adapter<BadgeAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemBadgeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun initViews(badge: Badge) {

        }

        fun bindViews(badge: Badge) {
            binding.badgeTitleView.text = badge.title
            binding.badgeDateView.text = badge.date
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBadgeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initViews(badges[position])
        holder.bindViews(badges[position])
    }

    override fun getItemCount(): Int {
        return badges.size
    }
}