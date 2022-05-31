package com.example.strap.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.strap.databinding.ItemCommunityRankBinding
import com.example.strap.entity.User

class CommunityRankItemAdapter :
    RecyclerView.Adapter<CommunityRankItemAdapter.ViewHolder>() {
    private var communityMembers: List<User> = listOf()

    inner class ViewHolder(private val binding: ItemCommunityRankBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindViews(item: User) = with(binding) {
            usernameTextView.text = item.name

            Glide.with(userProfile)
                .load(item.profileImage)
                .centerCrop()
                .into(userProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommunityRankBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(communityMembers[position])
    }

    override fun getItemCount(): Int = communityMembers.size

    fun submitList(items: List<User>) {
        communityMembers = items
        notifyDataSetChanged()
    }
}