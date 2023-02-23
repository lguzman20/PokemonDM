package com.luisguzman.pokemondm.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisguzman.pokemondm.databinding.ItemRegionBinding
import com.luisguzman.pokemondm.domain.main.model.response.Region
import java.util.*

class RegionAdapter(
    private var listRegion: List<Region>,
    private val onClick: (Region) -> Unit
) : RecyclerView.Adapter<RegionAdapter.RegionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
        val binding = ItemRegionBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RegionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
       return holder.bind(listRegion[position], onClick)
    }

    override fun getItemCount(): Int = listRegion.size

    inner class RegionViewHolder(private val binding: ItemRegionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Region, onClick: (Region) -> Unit) {

            binding.name.text = item.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }

            binding.root.setOnClickListener { onClick(item) }
        }
    }

}