package com.example.sciflaretask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sciflaretask.databinding.UserListItemBinding
import com.example.sciflaretask.dto.UserDetails

class UserAdapter(private val userList: List<UserDetails>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var expandedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position], position)
        holder.itemView.setOnClickListener {
            toggleItem(position)
        }
    }

    override fun getItemCount() = userList.size

    inner class ViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserDetails, position: Int) {
            val isExpanded = position == expandedPosition
            binding.apply {
                userGender.visibility = if (isExpanded) View.VISIBLE else View.GONE
                userMobile.visibility = if (isExpanded) View.VISIBLE else View.GONE
                personalDetails.visibility = if (isExpanded) View.VISIBLE else View.GONE
                userName.text = user.name?.trim()
                userMail.text = user.email
                userGender.text = user.gender
                userMobile.text = user.mobile
                userNameInitial.text = user.name?.trim()?.substring(0, 1)?.uppercase()
            }
        }
    }

    fun updateData(userList: List<UserDetails>) {
        notifyItemRangeChanged(0, userList.size)
    }

    private fun toggleItem(position: Int) {
        if (expandedPosition != RecyclerView.NO_POSITION) {
            notifyItemChanged(expandedPosition)
        }
        expandedPosition = if (expandedPosition == position) {
            RecyclerView.NO_POSITION
        } else {
            position
        }
        notifyItemChanged(position)
    }
}
