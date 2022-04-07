package com.example.appexamccp.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appexamccp.R
import com.example.appexamccp.databinding.UserItemBinding
import com.example.appexamccp.webService.responses.SingleUserResponse

class UserAdapter (var itemsToDisplay: ArrayList<SingleUserResponse>): RecyclerView.Adapter<UserAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.render(itemsToDisplay[position])
    }

    override fun getItemCount(): Int {
        return itemsToDisplay.size
    }

    inner class ItemHolder(private val view: View): RecyclerView.ViewHolder(view){
        val binding = UserItemBinding.bind(view)

        fun render(item: SingleUserResponse){
            binding.apply {
                userCompany.text = item.name
                userCompany.text = item.company
                Glide.with(view.context)
                    .load(item.avatarUrl)
                    .into(userImage)
            }
        }
    }

    fun setList(item: SingleUserResponse){
        this.itemsToDisplay.add(item)
        notifyDataSetChanged()
    }
}