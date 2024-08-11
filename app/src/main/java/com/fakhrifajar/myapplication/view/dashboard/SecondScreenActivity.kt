package com.fakhrifajar.myapplication.view.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fakhrifajar.myapplication.R
import com.fakhrifajar.myapplication.databinding.ActivitySecondScreenBinding
import com.fakhrifajar.myapplication.utils.NAME
import com.fakhrifajar.myapplication.view.ViewModelFactory

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySecondScreenBinding
    private val sharedViewModel: SharedViewModel by viewModels { ViewModelFactory.getInstance(this) }

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

            sharedViewModel.selectedUserName.observe(this@SecondScreenActivity) { name ->
                Log.d("INI NAMAKU", name.toString())
                binding.selectedUserName.text = name ?: getString(R.string.selected_user_name)
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