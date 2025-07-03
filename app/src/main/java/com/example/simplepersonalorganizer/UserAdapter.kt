package com.example.simplepersonalorganizer  //2023EBCS451--BITSID

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplepersonalorganizer.databinding.UserListItemBinding

class UserAdapter(private val userList: List<Pair<String, String>>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val (name, email) = userList[position]
        holder.binding.tvName.text = name
        holder.binding.tvEmail.text = email
    }

    override fun getItemCount(): Int = userList.size
}
