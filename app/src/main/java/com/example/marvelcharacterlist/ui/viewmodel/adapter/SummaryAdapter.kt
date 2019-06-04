package com.example.marvelcharacterlist.ui.viewmodel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelcharacterlist.R
import com.example.marvelcharacterlist.databinding.LayoutSummaryItemBinding

class SummaryAdapter(private val items: List<String>) : RecyclerView.Adapter<SummaryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutSummaryItemBinding>(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_summary_item
    }

    class ViewHolder(private val binding: LayoutSummaryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(summary: String) {
            binding.summary = summary
        }
    }
}