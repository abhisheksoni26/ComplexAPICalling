package com.example.complexapicalling.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.complexapicalling.R
import com.example.complexapicalling.databinding.ActivityMainBinding

class AbilityActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}