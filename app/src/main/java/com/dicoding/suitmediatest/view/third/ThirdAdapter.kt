package com.dicoding.suitmediatest.view.third

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.suitmediatest.databinding.ItemUserBinding

class ThirdAdapter : RecyclerView.Adapter<ThirdAdapter.ThirdViewHolder>() {
    private val list = ArrayList<DataItem>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(users: List<DataItem>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class ThirdViewHolder(private val binding: ItemUserBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar)
                    .into(ivUser)
                tvName.text = "${user.firstName} ${user.lastName}"
                tvEmail.text = user.email
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ThirdAdapter.ThirdViewHolder {
        val view =ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThirdViewHolder(view)
    }

    override fun onBindViewHolder(holder: ThirdAdapter.ThirdViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem)
    }

}