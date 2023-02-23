package com.luisguzman.pokemondm.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisguzman.pokemondm.databinding.ItemRegionDetailBinding
import com.luisguzman.pokemondm.domain.main.model.response.Pokedex
import java.util.*

class PokedexAdapter(
    private val listRegionDetail: List<Pokedex>,
    private val onClick: (Pokedex) -> Unit
) : RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        val binding = ItemRegionDetailBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PokedexViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        return holder.bind(listRegionDetail[position], onClick)
    }

    override fun getItemCount(): Int = listRegionDetail.size

    inner class PokedexViewHolder(private val binding: ItemRegionDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pokedex, onClick: (Pokedex) -> Unit) {

            binding.regionName.text = item.name.replace("-", " ")
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

            binding.root.setOnClickListener { onClick(item) }
        }
    }

}