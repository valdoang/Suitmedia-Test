package com.dicoding.suitmediatest.view.first

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.dicoding.suitmediatest.R
import com.dicoding.suitmediatest.databinding.ActivityFirstBinding
import com.dicoding.suitmediatest.utils.NAME
import com.dicoding.suitmediatest.view.second.SecondActivity
import com.google.android.material.snackbar.Snackbar

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.btnCheck.setOnClickListener {
            val palindrome = binding.edPalindrome.text.toString().trim()
            val layout = binding.firstLayout

            if (palindrome.equals(palindrome.reversed(), true)){
                Snackbar.make(
                    layout,
                    getString(R.string.isPalindrome),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            else {
                Snackbar.make(
                    layout,
                    getString(R.string.not_palindrome),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnNext.setOnClickListener {
            val name = binding.edName.text.toString().trim()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(NAME, name)
            startActivity(intent)
        }
    }
}