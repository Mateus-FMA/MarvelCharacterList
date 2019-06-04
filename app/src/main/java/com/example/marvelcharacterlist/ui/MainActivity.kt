package com.example.marvelcharacterlist.ui

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelcharacterlist.R
import com.example.marvelcharacterlist.databinding.ActivityMainBinding
import com.example.marvelcharacterlist.ui.viewmodel.adapter.CharacterListAdapter
import org.koin.android.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter = CharacterListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = getViewModel()

        binding.viewModel?.let { vm ->
            vm.charactersLiveData.observe(this, Observer { adapter.items = it ?: mutableListOf() })
            vm.reset()
            vm.load(onRefresh = { binding.progressBar.visibility = View.GONE })
        }

        // Setup recycler view.
        binding.rvCharacters.layoutManager = LinearLayoutManager(this)
        binding.rvCharacters.adapter = adapter
        binding.rvCharacters.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val llm = recyclerView.layoutManager as? LinearLayoutManager
                if (llm?.findLastVisibleItemPosition() == adapter.items.lastIndex &&
                        binding.viewModel?.loading?.get() == false)
                    binding.viewModel?.load()
            }

        })

        // Setup swipe refresh.
        binding.swipeRefresh.setOnRefreshListener {
            binding.viewModel?.reset()
            binding.viewModel?.load(onRefresh = { binding.swipeRefresh.isRefreshing = false})
        }
    }

    override fun onDestroy() {
        binding.viewModel?.disposeAll()
        super.onDestroy()
    }
}
