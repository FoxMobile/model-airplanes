package com.modelairplanes.ui.master

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.modelairplanes.R
import com.modelairplanes.databinding.MasterItemBinding
import com.modelairplanes.model.User


class MasterAdapter(val userList: List<User>, val masterInteraction: MasterInteraction) :
    RecyclerView.Adapter<MasterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MasterItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, masterInteraction)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    inner class ViewHolder(
        private val binding: MasterItemBinding,
        private val masterInteraction: MasterInteraction
    ) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(user: User) = with(binding) {
            binding.name.text = binding.root.context.getString(R.string.user_name_data, user.name)
            binding.email.text =
                binding.root.context.getString(R.string.user_email_data, user.email)
            binding.content.setOnClickListener {
                masterInteraction.onClickUser(user)
            }
        }
    }
}