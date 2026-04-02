package com.reelbreaker.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reelbreaker.databinding.ItemStatBinding

class SimpleMapAdapter : RecyclerView.Adapter<SimpleMapAdapter.VH>() {
    private var entries: List<Pair<String, Int>> = emptyList()

    fun submit(map: Map<String, Int>) {
        entries = map.entries.map { it.key to it.value }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemStatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(entries[position])
    override fun getItemCount(): Int = entries.size

    class VH(private val binding: ItemStatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Pair<String, Int>) {
            binding.tvKey.text = data.first
            binding.tvValue.text = data.second.toString()
        }
    }
}
