package com.luisguzman.pokemondm.ui.main.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisguzman.pokemondm.R
import com.luisguzman.pokemondm.databinding.ItemPokemonBinding
import com.luisguzman.pokemondm.domain.main.model.response.Pokemon
import com.luisguzman.pokemondm.utils.AppConstant.URL_RAW
import com.squareup.picasso.Picasso

class PokemonAdapter(
    private val listPokemon: List<Pokemon>,
    private val onClick: (Pokemon) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        return holder.bind(listPokemon[position], onClick)
    }

    override fun getItemCount(): Int = listPokemon.size

    inner class PokemonViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
            @SuppressLint("SetTextI18n")
            fun bind(item: Pokemon, onClick: (Pokemon) -> Unit) {
                val pokemonImg = "$URL_RAW${item.entryNumber}.png"
                val format = "N° ${item.entryNumber.toString().padStart(3,'0')}"

                binding.tvId.text = format
                binding.tvName.text = item.pokemonSpecies.name
                Picasso.get().load(pokemonImg)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
                    .into(binding.ivPokemon)

                binding.root.setOnClickListener { onClick(item) }
            }
        }
}