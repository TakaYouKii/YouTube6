package com.example.youtube6_3.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.youtube6_3.R

import com.example.youtube6_3.databinding.ActivityMainBinding
import org.koin.android.BuildConfig


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}