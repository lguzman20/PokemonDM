package com.luisguzman.pokemondm.ui.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.luisguzman.pokemondm.data.network.source.model.PokemonInfo
import com.luisguzman.pokemondm.domain.main.HomeRepository
import com.luisguzman.pokemondm.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    fun createTeams(teamName:String, id: Int, image: String, name:String) {
        FirebaseAuth.getInstance().currentUser?.uid.let { uid ->
            FirebaseFirestore.getInstance().collection("pokemos").document(uid.toString())
                .collection("teams").document(teamName).set(PokemonInfo(id, image, name))
        }
    }
    

    fun getAllRegion() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(State.Loading())
        try {
          emit(State.Success(repository.getRegions()))
        } catch (e:Exception) {
            emit(State.Failure(e))
        }
    }

    fun getRegion(region:String) = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(State.Loading())
        try {
            emit(State.Success(repository.getDetailedRegion(region)))
        } catch (e:Exception) {
            emit(State.Failure(e))
        }
    }

    fun getPokemons(pokedex: String) = liveData(Dispatchers.IO) {
        kotlinx.coroutines.delay(2000)
        emit(State.Loading())
        try {
            emit(State.Success(repository.getPokedex(pokedex)))
        } catch (e:Exception) {
            emit(State.Failure(e))
        }
    }

    fun getAllPokemonsDetails(id:Int) = liveData(Dispatchers.IO) {
        emit(State.Loading())
        try {
            emit(State.Success(repository.getPokeDetails(id)))
        } catch (e:Exception) {
            emit(State.Failure(e))
        }
    }


}