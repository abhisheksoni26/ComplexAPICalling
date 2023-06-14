package com.example.complexapicalling.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.complexapicalling.R
import com.example.complexapicalling.databinding.PokemonListBinding
import com.example.complexapicalling.model.PokemonModel
import com.example.complexapicalling.ui.AbilityActivity
import com.example.complexapicalling.ui.MainActivity
import com.example.complexapicalling.viewmodel.NamesViewModel

class NamesAdapter(var context:Context,var list:ArrayList<PokemonModel>) : RecyclerView.Adapter<NamesAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = PokemonListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.tvPokemonName.text=list[position].name



        holder.itemView.setOnClickListener {
            var splittedArray=list[position].url?.split("/")
           /* if (splittedArray != null) {
                for (item in splittedArray){
                    Log.e("ADAPTER", "onBindViewHolder: $item" )

                }
            }*/
            val intent= Intent(context,AbilityActivity::class.java)
            intent.putExtra("NUM", splittedArray?.last())
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
       return list.size
    }

    class ViewHolder(val binding: PokemonListBinding): RecyclerView.ViewHolder(binding.root) {

    }
}