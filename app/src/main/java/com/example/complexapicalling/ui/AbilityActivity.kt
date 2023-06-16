package com.example.complexapicalling.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.complexapicalling.adapter.AbilityAdapter
import com.example.complexapicalling.adapter.ImageAdapter
import com.example.complexapicalling.databinding.ActivityAbilityBinding
import com.example.complexapicalling.interfaces.ApiCall
import com.example.complexapicalling.model.AbilityName
import com.example.complexapicalling.model.SpritesModel
import com.example.complexapicalling.repository.PokemonRepo
import com.example.complexapicalling.service.RetrofitService
import com.example.complexapicalling.viewmodel.NamesViewModel
import com.example.complexapicalling.viewmodel.NamesViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AbilityActivity : AppCompatActivity() {

    private val binding: ActivityAbilityBinding by lazy {
        ActivityAbilityBinding.inflate(layoutInflater)
    }

    lateinit var namesViewModel: NamesViewModel

    lateinit var adapter: AbilityAdapter
    lateinit var imgAdapter: ImageAdapter
    lateinit var abilityList: ArrayList<AbilityName>
    lateinit var imgList: ArrayList<String>
    private var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        abilityList = ArrayList()
        imgList = ArrayList()

        //RecyclerView Setup
        adapter = AbilityAdapter(this, abilityList)
        binding.rvPokemonAbility.adapter = adapter

        imgAdapter = ImageAdapter(this, imgList)
        binding.rvPokemonImage.adapter = imgAdapter

        intent?.let { itIntent ->
            run {
                if (itIntent.hasExtra("POKEMON_ID")) {
                    id = itIntent.getStringExtra("POKEMON_ID").toString()

                    Log.e("TAG", "onCreate: $id", )
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

            getAbilityList()
        }

        viewObserver()

    }

    private fun viewObserver() {
        namesViewModel.abilityName.observe(this){
            if (!it.abilities.isNullOrEmpty()){
                for (item in it.abilities){
                    abilityList.add(item.ability)
                }
                it.sprites.backDefault?.let {
                    imgList.add(it)
                }
                it.sprites.backFemale?.let {
                    imgList.add(it)
                }
                it.sprites.backShiny?.let {
                    imgList.add(it)
                }
                it.sprites.backShinyFemale?.let {
                    imgList.add(it)
                }
                it.sprites.frontDefault?.let {
                    imgList.add(it)
                }
                it.sprites.frontFemale?.let {
                    imgList.add(it)
                }
                it.sprites.frontShiny?.let {
                    imgList.add(it)
                }

                it.sprites.frontShinyFemale?.let {
                    imgList.add(it)
                }

                Log.e("TAG", "viewObserver: ${it.abilities.toString()}", )
                adapter.notifyDataSetChanged()
                imgAdapter.notifyDataSetChanged()
            }
        }

        /*        namesViewModel._message.observe(this) {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }*/
    }

    private fun getAbilityList() {
        CoroutineScope(Dispatchers.IO).launch {
            namesViewModel.getAbilityPokemon(id.toInt())
        }
    }
}