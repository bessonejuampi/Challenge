package com.example.challengessrflextech.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.challengessrflextech.R
import com.example.challengessrflextech.data.models.Cat
import com.example.challengessrflextech.databinding.ActivityMainBinding
import com.example.challengessrflextech.ui.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getImagesRelated(query)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
               return false
            }
        })


        viewModel.cats.observe(this, Observer{ cats ->
            setupList(cats)
        })
    }

    private fun setupList(listCat:List<Cat>){
        binding.rvCats.layoutManager = GridLayoutManager(this, 2)
        val adapter = CatsAdapter(listCat)

        binding.ivIconCat.visibility = View.GONE
        binding.rvCats.visibility = View.VISIBLE

        binding.rvCats.adapter = adapter

        adapter.onItemClicked = {item ->

        }
    }
}
