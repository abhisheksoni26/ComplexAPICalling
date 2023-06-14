package com.example.complexapicalling.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.complexapicalling.interfaces.ApiCall
import com.example.complexapicalling.interfaces.NamesInterface
import com.example.complexapicalling.model.BaseClass
import com.example.complexapicalling.model.PokemonModel
import com.example.complexapicalling.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepo(private val apiCall: ApiCall) {


    suspend fun getPokemonNames(
        offset: Int,
        limit: Int,
        callback: NamesInterface<List<PokemonModel>>
    ) {
        apiCall.getNames(offset, limit).enqueue(object : Callback<BaseClass> {
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
                        callback.errorMessage("your fucking response is null AF")
                    }
                } else {
                    callback.response(null)
                    callback.errorMessage("Your api is fucked up...")
                }
            }

            override fun onFailure(call: Call<BaseClass>, t: Throwable) {
                callback.response(null)
                callback.errorMessage(t.message)
            }
        })


    }

}