package com.example.complexapicalling.repository

import android.util.Log
import com.example.complexapicalling.interfaces.ApiCall
import com.example.complexapicalling.interfaces.ResponseCallback
import com.example.complexapicalling.model.BaseAbility
import com.example.complexapicalling.model.BaseClass
import com.example.complexapicalling.model.DataModel
import com.example.complexapicalling.model.PokemonModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepo(private val apiCall: ApiCall) {

    fun getPokemonNames(
        offset: Int,
        limit: Int,
        callback: ResponseCallback<List<PokemonModel>>
    ) {

        apiCall.getNames(offset, limit)
            .enqueue(object : Callback<BaseClass> {
            override fun onResponse(
                call: Call<BaseClass>,
                response: Response<BaseClass>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.response(it.results)
                        callback.errorMessage(null)
                    } ?: {
                        callback.response(null)
                        callback.errorMessage("your response is null")
                    }
                } else {
                    callback.response(null)
                    callback.errorMessage("Api is not fetched...")
                }
            }

            override fun onFailure(call: Call<BaseClass>, t: Throwable) {
                callback.response(null)
                callback.errorMessage(t.message)
                Log.e("TAG", "onFailure: ${t.message}", )
            }
        })


    }

    fun getAbilityDetails(
        id: Int,
        callback: ResponseCallback<BaseAbility>
    ) {
        apiCall.getAbilities(id)
            .enqueue(object : Callback<BaseAbility>{
                override fun onResponse(call: Call<BaseAbility>, response: Response<BaseAbility>) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            callback.response(it)
                            callback.errorMessage(null)
                        } ?:{
                            callback.response(null)
                            callback.errorMessage("Response is null")
                        }
                    }
                    else{
                        callback.response(null)
                        callback.errorMessage("Api not fetched...")
                    }
                }

                override fun onFailure(call: Call<BaseAbility>, t: Throwable) {
                    callback.response(null)
                    callback.errorMessage(t.message)
                    Log.e("TAG", "onFailure: ${t.message}", )
                }

            })

    }

    fun getDataPokemon(
        id: Int,
        callback: ResponseCallback<DataModel>
    ) {
        apiCall.getData(id)
            .enqueue(object : Callback<DataModel>{
                override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            callback.response(it)
                            callback.errorMessage(null)
                        } ?:{
                            callback.response(null)
                            callback.errorMessage("Response is null")
                        }
                    }
                    else{
                        callback.response(null)
                        callback.errorMessage("Api not fetched...")
                    }
                }

                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                    callback.response(null)
                    callback.errorMessage(t.message)
                    Log.e("TAG", "onFailure: ${t.message}", )
                }

            })

    }

}