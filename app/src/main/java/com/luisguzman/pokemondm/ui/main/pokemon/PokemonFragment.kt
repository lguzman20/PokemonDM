package com.luisguzman.pokemondm.ui.main.pokemon

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.luisguzman.pokemondm.R
import com.luisguzman.pokemondm.data.network.source.model.PokemonInfo
import com.luisguzman.pokemondm.databinding.FragmentPokemonBinding
import com.luisguzman.pokemondm.domain.main.model.response.Pokedex
import com.luisguzman.pokemondm.domain.main.model.response.Pokemon
import com.luisguzman.pokemondm.ui.main.adapters.PokemonAdapter
import com.luisguzman.pokemondm.ui.main.viewModel.MainViewModel
import com.luisguzman.pokemondm.utils.State
import com.luisguzman.pokemondm.utils.errorToastMessage
import com.luisguzman.pokemondm.utils.gone
import com.luisguzman.pokemondm.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonFragment : Fragment(R.layout.fragment_pokemon) {

    private lateinit var binding: FragmentPokemonBinding
    private val viewModel by viewModels<MainViewModel>()
    private val args: PokemonFragmentArgs by navArgs()
    private var teamName = ""
    private var listPokemon = listOf<Pokedex>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPokemonBinding.bind(view)
        observes()
        setListeners()

    }

    private fun setListeners() {
        binding.back.setOnClickListener { findNavController().popBackStack() }

        binding.btnCreateTeam.setOnClickListener {
            dialogCreateTeam()
        }

    }

    private fun observes() {
        viewModel.getRegion(args.region).observe(viewLifecycleOwner) { result ->
            when(result) {
                is State.Loading -> {}
                is State.Success -> {
                 result.metaData?.let { listPokemon = it }
                    observeApiStatus(listPokemon[0].name)
                }
                is State.Failure -> {
                    errorToastMessage(requireContext(),"Error")
                }
            }
        }
    }

    private fun observeApiStatus(subRegion: String) {
        viewModel.getPokemons(subRegion).observe(viewLifecycleOwner) { result ->
            when (result) {
                is State.Loading -> {
                    binding.statusOffline.gone()
                    binding.shimmerLoading.visible()
                    binding.recyclerViewPoke.gone()
                }
                is State.Success -> {
                    binding.statusOffline.gone()
                    binding.shimmerLoading.gone()
                    binding.recyclerViewPoke.visible()
                    setUpRecyclerView(result.metaData)
                }
                is State.Failure -> {
                    binding.statusOffline.visible()
                    binding.shimmerLoading.gone()
                    binding.recyclerViewPoke.gone()
                }
            }
        }
    }

    private fun setUpRecyclerView(data: List<Pokemon>?) {
        binding.recyclerViewPoke.adapter = data?.let { listPokemon ->
            PokemonAdapter(listPokemon = listPokemon, onClick = { navigationView(it) }) }
    }

    private fun navigationView(data: Pokemon) {
        findNavController().navigate(PokemonFragmentDirections.actionPokemonFragmentToDetailFragment(
            data.entryNumber
        ))
    }

    @SuppressLint("InflateParams")
    private fun dialogCreateTeam() {
        val dialogBinding = layoutInflater.inflate(R.layout.dialog_create_team, null)
        val dialog = Dialog(requireContext())
        dialog.setCancelable(true)
        dialog.setContentView(dialogBinding)
        dialog.window?.setLayout(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val fieldTeam = dialogBinding.findViewById<TextInputEditText>(R.id.editTeamName)
        teamName = fieldTeam.text.toString().trim()

        val signUpButton = dialogBinding.findViewById<MaterialButton>(R.id.btnCreate)
        signUpButton.setOnClickListener {
            dialog.dismiss()
            viewModel.createTeams(teamName, id = 0, image = "", name = "")
        }

    }

}