package com.example.complexapicalling.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.complexapicalling.interfaces.NamesInterface
import com.example.complexapicalling.model.PokemonModel
import com.example.complexapicalling.repository.PokemonRepo

class NamesViewModel(private var repository: PokemonRepo) : ViewModel() {


    var names: LiveData<List<PokemonModel>>
    var _name: MutableLiveData<List<PokemonModel>> = MutableLiveData()


    private var message: LiveData<String>
    var _message: MutableLiveData<String> = MutableLiveData()


    init {
        names = _name
        message = _message
    }


    suspend fun getPokeMonList(offset:Int,limit:Int) {
        repository.getPokemonNames(offset,limit,object : NamesInterface<List<PokemonModel>> {
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