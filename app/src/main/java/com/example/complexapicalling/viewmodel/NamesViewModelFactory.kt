package com.example.complexapicalling.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.complexapicalling.repository.PokemonRepo

class NamesViewModelFactory(private var repository: PokemonRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NamesViewModel::class.java)) {
            return NamesViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Not assigned from PokeMon View Model class")
        }
    }

}