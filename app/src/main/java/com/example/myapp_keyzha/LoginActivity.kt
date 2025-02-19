package com.example.myapp_keyzha

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Pastikan XML bernama "activity_login"

        // Initialize views
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.password)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val register = findViewById<TextView>(R.id.register)

        // Instance DatabaseHelper
        val dbHelper = DatabaseHelper(this)

        // Navigasi ke Register
        register.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // Proses Login
        btnLogin.setOnClickListener {
            val enteredUsername = etUsername.text.toString().trim()
            val enteredPassword = etPassword.text.toString().trim()

            if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                Toast.makeText(this, "Username atau password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                // Cek di database SQLite
                if (dbHelper.checkUser(enteredUsername, enteredPassword)) {
                    Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}