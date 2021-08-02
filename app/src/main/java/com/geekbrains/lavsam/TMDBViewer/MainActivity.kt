package com.geekbrains.lavsam.TMDBViewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.lavsam.TMDBViewer.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(binding.container.id, )
//        }
    }
}