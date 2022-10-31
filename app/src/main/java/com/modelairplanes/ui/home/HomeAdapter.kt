package com.modelairplanes.ui.home

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.modelairplanes.databinding.HomeItemBinding
import com.modelairplanes.model.Payment


class HomeAdapter(val paymentList: List<Payment>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HomeItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return paymentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(paymentList[position])
    }

    inner class ViewHolder(private val binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private fun downloadFile(
            fileName: String,
            fileExtension: String,
            destinationDirectory: String?,
            url: String?
        ): Long {
            val downloadmanager =
                binding.root.context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val uri: Uri = Uri.parse(url)
            val request = DownloadManager.Request(uri)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalFilesDir(
                binding.root.context,
                destinationDirectory,
                fileName + fileExtension
            )
            return downloadmanager.enqueue(request)
        }

        fun bind(payment: Payment) = with(binding) {
            binding.date.text = payment.datePayment.toString()
            binding.content.setOnClickListener {
                downloadFile(
                    "payment",
                    ".pdf",
                    Environment.DIRECTORY_DOWNLOADS,
                    payment.url_document
                )
            }
        }
    }
}