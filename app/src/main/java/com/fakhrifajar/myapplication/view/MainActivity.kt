package com.fakhrifajar.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fakhrifajar.myapplication.R
import com.fakhrifajar.myapplication.databinding.ActivityMainBinding
import com.fakhrifajar.myapplication.utils.NAME
import com.fakhrifajar.myapplication.view.dashboard.SecondScreenActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpAction()

    }

    private fun setUpAction() {
        binding.apply {
            edtName.onFocusChangeListener = focusChangeListener
            edtPalindrome.onFocusChangeListener = focusChangeListener

            btnCheck.setOnClickListener {
                val palindrome = edtPalindrome.text.toString().trim()
                val state = checkIsPalindrome(palindrome)
                Log.d("Palindrome", state.toString())
                if (state) {
                    showDialog("Is Palindrome")
                } else {
                    showDialog("Not Palindrome")
                }
            }

            btnNext.setOnClickListener {
                val name = edtName.text.toString().trim()
                val intent = Intent(this@MainActivity, SecondScreenActivity::class.java)
                intent.putExtra(NAME, name)
                startActivity(intent)
            }
        }
    }

    private fun checkIsPalindrome(text: String): Boolean {
        val cleanedText = text.replace(" ", "")
        val reverseString = cleanedText.reversed()
        Log.d("Palindrome", reverseString)
        Log.d("Palindrome Cleaned", cleanedText)
        return cleanedText.equals(reverseString, ignoreCase = true)
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss()}
            .create()
            .show()
    }

    private val focusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
        if (view.id == R.id.edt_name) {
            if (hasFocus) {
                binding.nameInputLayout.hint = null
            } else {
                if (binding.edtName.text.toString().isEmpty()) {
                    binding.nameInputLayout.hint = getString(R.string.user_name)
                }
            }

        }
        if (view.id == R.id.edt_palindrome) {
            if (hasFocus) {
                binding.palindromeInputLayout.hint = null
            } else {
                if (binding.edtName.text.toString().isEmpty()) {
                    binding.palindromeInputLayout.hint = getString(R.string.palindrome)
                }
            }

        }
    }

}