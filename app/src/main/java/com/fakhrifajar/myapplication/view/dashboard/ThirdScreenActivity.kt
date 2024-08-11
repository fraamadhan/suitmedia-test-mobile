package com.fakhrifajar.myapplication.view.dashboard

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhrifajar.myapplication.R
import com.fakhrifajar.myapplication.adapter.LoadingStateAdapter
import com.fakhrifajar.myapplication.adapter.UserAdapter
import com.fakhrifajar.myapplication.databinding.ActivityThirdScreenBinding
import com.fakhrifajar.myapplication.view.ViewModelFactory

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityThirdScreenBinding
    private val viewModel: ThirdScreenViewModel by viewModels<ThirdScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private val sharedViewModel: SharedViewModel by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        val adapter = UserAdapter { user ->
            val fullName = "${user.firstName} ${user.lastName}"
            sharedViewModel.setSelectedUserName(fullName)
        }
        binding.rvUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )

        viewModel.users.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }
}