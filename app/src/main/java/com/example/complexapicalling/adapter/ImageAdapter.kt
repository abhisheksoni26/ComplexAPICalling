package com.example.complexapicalling.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.complexapicalling.R
import com.example.complexapicalling.databinding.ItemviewImglistBinding
import com.example.complexapicalling.model.AbilityName

class ImageAdapter(var context: Context, var abilityList: ArrayList<String>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemviewImglistBinding>(
            LayoutInflater.from(parent.context), R.layout.itemview_imglist, parent, false
        )
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {

        Glide
            .with(context)
            .load(abilityList[position])
            .into(holder.binding.ivPokemon)
    }

    override fun getItemCount(): Int {
        return abilityList.size
    }

    class ViewHolder(val binding: ItemviewImglistBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}