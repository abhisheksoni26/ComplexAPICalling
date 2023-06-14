package com.example.complexapicalling.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.complexapicalling.adapter.NamesAdapter
import com.example.complexapicalling.databinding.ActivityMainBinding
import com.example.complexapicalling.interfaces.ApiCall
import com.example.complexapicalling.model.PokemonModel
import com.example.complexapicalling.repository.PokemonRepo
import com.example.complexapicalling.service.RetrofitService
import com.example.complexapicalling.viewmodel.NamesViewModel
import com.example.complexapicalling.viewmodel.NamesViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.jar.Attributes.Name

class MainActivity : AppCompatActivity() {

    lateinit var namesViewModel: NamesViewModel


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var list: ArrayList<PokemonModel>
    lateinit var adapter: NamesAdapter
    private var page=0
    private var length=20
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        list = ArrayList()
        adapter = NamesAdapter(this, list)


        //ViewModel Setup
        val retrofitService = RetrofitService.retrofit.
        val repository = PokemonRepo(retrofitService)

        namesViewModel = ViewModelProvider(
            this,
            NamesViewModelFactory(repository)
        ).get(NamesViewModel::class.java)

        getPokemonList()
        viewObserver()

        //RecyclerView Setup

        binding.rvPokemonList.adapter = adapter
    }

    private fun viewObserver() {
        namesViewModel.names.observe(this@MainActivity) {
            list.addAll(it)
            adapter.notifyDataSetChanged()
        }

/*        namesViewModel._message.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }*/
    }


    private fun getPokemonList() {
        CoroutineScope(Dispatchers.IO).launch {
            namesViewModel.getPokeMonList(page,length)
        }
    }
}