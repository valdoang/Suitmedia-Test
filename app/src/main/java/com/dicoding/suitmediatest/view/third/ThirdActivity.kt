package com.dicoding.suitmediatest.view.third

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.suitmediatest.databinding.ActivityThirdBinding
import com.dicoding.suitmediatest.utils.FIRST_NAME
import com.dicoding.suitmediatest.utils.LAST_NAME
import com.dicoding.suitmediatest.utils.RESULT_CODE
import com.dicoding.suitmediatest.view.second.SecondActivity

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var thirdViewModel: ThirdViewModel
    private lateinit var adapter: ThirdAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupViewModel()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }

    private fun setupViewModel() {
        thirdViewModel = ThirdViewModel()
        adapter = ThirdAdapter()

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@ThirdActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter
        }

        adapter.setOnItemClickCallback(object : ThirdAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItem) {
                val resultIntent = Intent()
                resultIntent.putExtra(FIRST_NAME, data.firstName)
                resultIntent.putExtra(LAST_NAME, data.lastName)
                setResult(RESULT_CODE, resultIntent)
                finish()
            }

        })

        thirdViewModel.setUsers()
        thirdViewModel.getUsers().observe(this){
            if(it!=null){
                adapter.setList(it)
                binding.tvEmptyState.visibility = View.GONE
            }
        }

        thirdViewModel.isLoading.observe(this){
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}