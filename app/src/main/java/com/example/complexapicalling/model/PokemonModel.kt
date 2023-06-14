package com.example.complexapicalling.model

import com.google.gson.annotations.Expose

data class PokemonModel(
    @Expose
    var name: String?,


    @Expose
    var url: String?
)


data class BaseClass(

    var count:Int,
    var next:String?,
    var previous:String?,
    var results: ArrayList<PokemonModel>
    )


