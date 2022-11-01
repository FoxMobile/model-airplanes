package com.modelairplanes.ui.master.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.modelairplanes.databinding.HomeItemBinding
import com.modelairplanes.model.Payment


class MasterListAdapter(val paymentList: List<Payment>, val interaction: MasterDetailInteraction) :
    RecyclerView.Adapter<MasterListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HomeItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, interaction)
    }

    override fun getItemCount(): Int {
        return paymentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(paymentList[position])
    }

    inner class ViewHolder(private val binding: HomeItemBinding,
    private val interaction: MasterDetailInteraction) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(payment: Payment) = with(binding) {
            binding.date.text = payment.datePayment.toString()
            binding.content.setOnClickListener {
                interaction.onClickInfo(payment)
            }
        }
    }
}