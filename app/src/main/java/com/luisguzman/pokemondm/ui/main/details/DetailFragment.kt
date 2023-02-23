package com.luisguzman.pokemondm.ui.main.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.luisguzman.pokemondm.R
import com.luisguzman.pokemondm.data.network.source.model.PokemonInfo
import com.luisguzman.pokemondm.databinding.FragmentDetailBinding
import com.luisguzman.pokemondm.databinding.FragmentDetailBinding.bind
import com.luisguzman.pokemondm.domain.main.model.response.PokeItemDetails
import com.luisguzman.pokemondm.ui.main.viewModel.MainViewModel
import com.luisguzman.pokemondm.utils.State
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding:  FragmentDetailBinding
    private val viewModel by viewModels<MainViewModel>()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = bind(view)
        setListeners()
        observeStatus()

    }

    private fun setListeners() {
        binding.back.setOnClickListener { findNavController().popBackStack() }
    }

    private fun observeStatus() {
        viewModel.getAllPokemonsDetails(id = args.id).observe(viewLifecycleOwner) { result ->
            when(result) {
                is State.Loading -> {
                    binding.loading.progressBar.visibility = View.VISIBLE
                    binding.appBar.visibility = View.GONE
                    binding.nestedScrollView.visibility = View.GONE
                    binding.statusOffline.visibility = View.GONE
                }
                is State.Success -> {
                    binding.loading.progressBar.visibility = View.GONE
                    binding.appBar.visibility = View.VISIBLE
                    binding.nestedScrollView.visibility = View.VISIBLE
                    binding.statusOffline.visibility = View.GONE
                    result.metaData?.let { pokemonDetails(it) }
                }
                is State.Failure -> {
                    binding.loading.progressBar.visibility = View.GONE
                    binding.appBar.visibility = View.GONE
                    binding.nestedScrollView.visibility = View.GONE
                    binding.statusOffline.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun pokemonDetails(pokemon: PokeItemDetails) {
        if (pokemon.types.size > 1) {
            binding.tvType1.text = pokemon.types[0]
            binding.tvType1
            binding.tvType2.text = pokemon.types[1]
            binding.tvType2.visibility = View.VISIBLE
        } else {
            binding.tvType1.text = pokemon.types[0]
            binding.tvType2.visibility = View.GONE
        }

        Picasso.get().load(pokemon.img)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(binding.image)

        binding.collapsingToolbar.title = pokemon.name
        binding.tvHp.text = pokemon.hp.toString()
        binding.speed.text = pokemon.speed.toString()
        binding.attack.text = pokemon.attack.toString()
        binding.defense.text = pokemon.defense.toString()
        binding.specialAttack.text = pokemon.specialAttack.toString()
        binding.specialDefense.text = pokemon.specialDefense.toString()
        binding.height.text = getString(R.string.metro, pokemon.height.toString())
        binding.weight.text = getString(R.string.kilo, pokemon.weight.toString())

    }

}