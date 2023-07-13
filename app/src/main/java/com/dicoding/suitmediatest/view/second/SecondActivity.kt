package com.dicoding.suitmediatest.view.second

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.suitmediatest.databinding.ActivitySecondBinding
import com.dicoding.suitmediatest.utils.FIRST_NAME
import com.dicoding.suitmediatest.utils.LAST_NAME
import com.dicoding.suitmediatest.utils.NAME
import com.dicoding.suitmediatest.utils.RESULT_CODE
import com.dicoding.suitmediatest.view.third.ThirdActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var selectedUsername: TextView
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == RESULT_CODE && result.data != null) {
            val firstName = result.data?.getStringExtra(FIRST_NAME)
            val lastName = result.data?.getStringExtra(LAST_NAME)
            selectedUsername.text = "$firstName $lastName"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupViewModel()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }

    private fun setupViewModel() {
        val name =intent.getStringExtra(NAME)
        binding.tvName.text = name

        selectedUsername = binding.tvSelectedUsername

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            resultLauncher.launch(intent)
        }

    }
}