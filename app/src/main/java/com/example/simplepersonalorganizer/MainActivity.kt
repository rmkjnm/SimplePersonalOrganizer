package com.example.simplepersonalorganizer

import android.content.Intent              // ← add this
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simplepersonalorganizer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Database
        db = DatabaseHelper(this)

        // Save button
        binding.btnSave.setOnClickListener {
            val name  = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()

            if (name.isEmpty() || email.isEmpty()) {
                toast("Both fields required.")
                return@setOnClickListener
            }

            if (db.insertUser(name, email)) {
                toast("Saved ✓")
                binding.etName.text?.clear()
                binding.etEmail.text?.clear()
            } else {
                toast("Insert failed.")
            }
        }

        // View‑all button → open list screen
        binding.btnView.setOnClickListener {
            startActivity(Intent(this, UserListActivity::class.java))
        }
    }

    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
