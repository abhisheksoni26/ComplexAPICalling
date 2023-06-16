package com.example.complexapicalling.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.complexapicalling.interfaces.ResponseCallback
import com.example.complexapicalling.model.BaseAbility
import com.example.complexapicalling.model.DataModel
import com.example.complexapicalling.model.PokemonModel
import com.example.complexapicalling.repository.PokemonRepo

class NamesViewModel(private var repository: PokemonRepo) : ViewModel() {



    var _name: MutableLiveData<List<PokemonModel>> = MutableLiveData()
    var _abilityName: MutableLiveData<BaseAbility> = MutableLiveData()
    var _dataPokemon: MutableLiveData<DataModel> = MutableLiveData()
    var _message: MutableLiveData<String> = MutableLiveData()


    var names: LiveData<List<PokemonModel>>
    val abilityName: LiveData<BaseAbility>
    var dataPokemon: LiveData<DataModel>
    var message: LiveData<String>



    init {
        names = _name
        abilityName = _abilityName
        dataPokemon = _dataPokemon
        message = _message
    }


    fun getDataPokemon(id: Int){
        repository.getDataPokemon(id, object  : ResponseCallback<DataModel>{
            override fun response(data: DataModel?) {
                data?.let {
                    _dataPokemon.postValue(it)
                }
            }

            override fun errorMessage(message: String?) {
                message?.let {
                    _message.postValue(it)
                }
            }

        })
    }

    fun getAbilityPokemon(id: Int){
        repository.getAbilityDetails(id, object : ResponseCallback<BaseAbility>{
            override fun response(data: BaseAbility?) {
                data?.let {
                    _abilityName.postValue(it)
                }
            }

            override fun errorMessage(message: String?) {
                message?.let {
                    _message.postValue(it)
                }
            }

        })
    }


     fun getPokeMonList(offset:Int,limit:Int) {
        repository.getPokemonNames(offset,limit,object : ResponseCallback<List<PokemonModel>> {
            override fun response(data: List<PokemonModel>?) {
                data?.let {
                    _name.postValue(it)
                }

            }

            override fun errorMessage(message: String?) {
                message?.let {
                    _message.postValue(it)
                }
            }
        })
    }


}