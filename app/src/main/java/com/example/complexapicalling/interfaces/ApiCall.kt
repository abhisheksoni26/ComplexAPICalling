package com.example.complexapicalling.interfaces

import com.example.complexapicalling.model.BaseClass
import com.example.complexapicalling.model.PokemonModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {

    @GET("pokemon/")
    fun getNames(@Query("offset")offset:Int,@Query("limit")limit:Int) : Call<BaseClass>

    @GET("pokemon/{id}/")
    fun getAbilities(): Call

}