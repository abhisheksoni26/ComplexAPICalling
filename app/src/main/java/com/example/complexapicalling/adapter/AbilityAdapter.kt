package com.example.complexapicalling.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.complexapicalling.databinding.AbilityListBinding
import com.example.complexapicalling.model.AbilityName
import com.example.complexapicalling.ui.DataActivity

class AbilityAdapter(var context: Context, var list: ArrayList<AbilityName>) : RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val binding = AbilityListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AbilityAdapter.ViewHolder, position: Int) {
        holder.sendData(list[position])

        holder.itemView.setOnClickListener {
            val splittedArray = list[position].url?.split("/")

            val last = splittedArray?.get(splittedArray.lastIndex - 1)

            val intent = Intent(context, DataActivity::class.java)
            intent.putExtra("ABILITY_ID", last)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {

        return list.size

    }

    class ViewHolder(val binding: AbilityListBinding): RecyclerView.ViewHolder(binding.root)  {

        fun sendData(data:AbilityName){
           binding.myData=data
        }
    }

}