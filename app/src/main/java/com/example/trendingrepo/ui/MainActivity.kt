package com.example.trendingrepo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.example.domain.model.RepoDataDetails
import com.example.trendingrepo.R
import com.example.trendingrepo.ui.adapter.RepoAdapter
import com.example.trendingrepo.databinding.ActivityMainBinding
import com.example.trendingrepo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : RepoAdapter
    private val viewModel: MainViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        if (!viewModel.dataFeched){
            viewModel.getTrendingRepo()
        }
        adapter = RepoAdapter(viewModel.repoList)
        binding.rvAdapter.adapter = adapter
        adapter.callbackListener = { data, position->
            if (data is RepoDataDetails){
                viewModel.originalList[position].isSelected = data.isSelected
                adapter.notifyItemChanged(position)
            }
        }

        binding.svSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.repoList.clear()
                    if (newText.isNullOrEmpty()){
                        viewModel.repoList.clear()
                        viewModel.repoList.addAll(viewModel.originalList)
                    }else{
                        viewModel.repoList.addAll(viewModel.originalList.filter{details->details.description?.contains(newText,true)?:false})
                    }
                return true
            }
        })
    }
}