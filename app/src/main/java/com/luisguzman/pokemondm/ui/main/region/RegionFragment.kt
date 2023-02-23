package com.luisguzman.pokemondm.ui.main.region

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.luisguzman.pokemondm.PokemonDmApp
import com.luisguzman.pokemondm.R
import com.luisguzman.pokemondm.databinding.FragmentRegionBinding
import com.luisguzman.pokemondm.domain.main.model.response.Pokedex
import com.luisguzman.pokemondm.domain.main.model.response.Region
import com.luisguzman.pokemondm.ui.main.adapters.RegionAdapter
import com.luisguzman.pokemondm.ui.main.viewModel.MainViewModel
import com.luisguzman.pokemondm.ui.splash.SplashActivity
import com.luisguzman.pokemondm.utils.State
import com.luisguzman.pokemondm.utils.errorToastMessage
import com.luisguzman.pokemondm.utils.gone
import com.luisguzman.pokemondm.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegionFragment : Fragment(R.layout.fragment_region) {

    private lateinit var binding: FragmentRegionBinding
    private val viewModel by viewModels<MainViewModel>()
    private var listRegion = listOf<Region>()
    private var listSubRegion = listOf<Pokedex>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegionBinding.bind(view)
        observers()
        logout()
    }

    private fun logout() {
        binding.btnLogOut.setOnClickListener {
            startActivity(Intent(requireContext(), SplashActivity::class.java))
            requireActivity().finish()
            PokemonDmApp.sharedPreferences.setLoginValue(false)
        }
    }

    private fun observers() {

        viewModel.getAllRegion().observe(viewLifecycleOwner) { result ->
            when (result) {
                is State.Loading -> {
                    binding.loadingRegion.visible()
                    binding.containerRegion.gone()
                }
                is State.Success -> {
                    binding.loadingRegion.gone()
                    binding.containerRegion.visible()
                    result.metaData?.let {
                        setUpRecyclerView(it)
                        listRegion = it
                    }
                }
                is State.Failure -> {
                    binding.loadingRegion.gone()
                    binding.containerRegion.visible()
                    errorToastMessage(requireContext(), "error")
                    Log.d("dataError", result.throwable.message.toString())
                }
            }
        }
    }

    private fun setUpRecyclerView(regions: List<Region>) {
        binding.rvRegion.adapter = RegionAdapter(
            listRegion = regions,
            onClick = { navigationView(it) })
    }

    private fun navigationView(data: Region) {
//        findNavController().navigate(RegionFragmentDirections.actionRegionFragmentToPokedexInfoFragment(
//            region = data.name
//        ))
        findNavController().navigate(RegionFragmentDirections.actionRegionFragmentToPokemonFragment(
            region = data.name
        ))
    }

}