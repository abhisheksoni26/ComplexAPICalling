package com.example.complexapicalling.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.complexapicalling.R
import com.example.complexapicalling.adapter.AbilityAdapter
import com.example.complexapicalling.adapter.DataAdapter
import com.example.complexapicalling.adapter.ImageAdapter
import com.example.complexapicalling.databinding.ActivityDataBinding
import com.example.complexapicalling.interfaces.ApiCall
import com.example.complexapicalling.model.Effects
import com.example.complexapicalling.repository.PokemonRepo
import com.example.complexapicalling.service.RetrofitService
import com.example.complexapicalling.viewmodel.NamesViewModel
import com.example.complexapicalling.viewmodel.NamesViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataActivity : AppCompatActivity() {

     private val binding: ActivityDataBinding by lazy {
        ActivityDataBinding.inflate(layoutInflater)
    }

    lateinit var dataList: ArrayList<Effects>
    lateinit var namesViewModel: NamesViewModel
    lateinit var adapter: DataAdapter

    private var id = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dataList = ArrayList()

        //RecyclerView Setup
        adapter = DataAdapter(dataList)
        binding.rvPokemonAbility.adapter = adapter

        intent?.let { itIntent ->
            run {
                if (itIntent.hasExtra("ABILITY_ID")) {
                    id = itIntent.getStringExtra("ABILITY_ID").toString()

                    Log.e("Abhishek", "onCreate: $id", )
                }
            }
        }

        //ViewModel Setup
        val retrofitService = RetrofitService.retrofit.create(ApiCall::class.java)
        val repository = PokemonRepo(retrofitService)

        namesViewModel = ViewModelProvider(
            this,
            NamesViewModelFactory(repository)
        ).get(NamesViewModel::class.java)


        if (!TextUtils.isEmpty(id)){
            getDataList()
        }

        viewObserver()



    }

    private fun viewObserver() {
        namesViewModel.dataPokemon.observe(this){
            if (!it.effectEntries.isNullOrEmpty()){

                dataList.addAll(it.effectEntries)
                adapter.notifyDataSetChanged()

            }
        }
    }

    private fun getDataList() {
        CoroutineScope(Dispatchers.IO).launch {
            namesViewModel.getDataPokemon(id.toInt())
        }
    }
}