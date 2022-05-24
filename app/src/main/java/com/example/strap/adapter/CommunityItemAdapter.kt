package com.example.strap.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.strap.databinding.ItemCommunityListBinding
import com.example.strap.entity.Community

class CommunityItemAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<CommunityItemAdapter.ViewHolder>() {
    private var communityItems: List<Community> = listOf()

    inner class ViewHolder(private val binding: ItemCommunityListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindViews(community: Community) = with(binding) {
            titleTextView.text = community.title
            memberTextView.text = "${community.member?.size!! + 1}명"

            Glide.with(profileImage)
                .load(community.image)
                .centerCrop()
                .into(profileImage)

            btnInfo.setOnClickListener { itemClickListener(position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommunityListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(communityItems[position])
    }

    override fun getItemCount(): Int = communityItems.size

    fun submitList(items: List<Community>) {
        communityItems = items
        notifyDataSetChanged()
    }
}