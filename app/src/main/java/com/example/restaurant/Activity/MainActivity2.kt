package com.example.restaurant.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurant.DbHelper
import com.example.restaurant.User
import com.example.restaurant.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegistr.setOnClickListener{
            val login = binding.editTextTextEmailAddress2.text.toString().trim()
            val password = binding.editTextTextPassword2.text.toString().trim()
            val role = 1

            if (login == "" || password == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            else {
                val user = User(login, password, role)

                val db = DbHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Пользователь $login зарегистрирован", Toast.LENGTH_LONG).show()
                binding.editTextTextEmailAddress2.text.clear()
                binding.editTextTextPassword2.text.clear()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
