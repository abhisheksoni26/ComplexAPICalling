package com.example.complexapicalling.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.complexapicalling.R
import com.example.complexapicalling.databinding.ItemviewEffectBinding
import com.example.complexapicalling.databinding.ItemviewImglistBinding
import com.example.complexapicalling.model.Effects

class DataAdapter(var list: ArrayList<Effects>): RecyclerView.Adapter<DataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemviewEffectBinding>(
            LayoutInflater.from(parent.context), R.layout.itemview_effect, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {
        holder.binding.tvEffect.text = list[position].effect
        holder.binding.tvShortEffect.text = list[position].shortEffect
        holder.binding.tvLangguage.text = list[position].language.name
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(val binding: ItemviewEffectBinding): RecyclerView.ViewHolder(binding.root){

    }


}