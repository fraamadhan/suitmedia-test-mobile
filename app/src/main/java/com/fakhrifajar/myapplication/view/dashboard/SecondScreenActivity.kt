package com.fakhrifajar.myapplication.view.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fakhrifajar.myapplication.R
import com.fakhrifajar.myapplication.databinding.ActivitySecondScreenBinding
import com.fakhrifajar.myapplication.utils.NAME

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayShowHomeEnabled(true)
                setDisplayHomeAsUpEnabled(true)
            }
            val name = intent.getStringExtra(NAME)
            if (name.isNullOrEmpty()) {
                userName.text = getString(R.string.name_holder)
            }
            else {
                userName.text = name
            }

            btnChoose.setOnClickListener {
                startActivity(Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java))
            }

        }


        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }

        })
    }
}