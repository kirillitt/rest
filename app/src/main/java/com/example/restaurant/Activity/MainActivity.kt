package com.example.restaurant.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurant.DbHelper
import com.example.restaurant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonToRegistr.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        binding.buttonToEnter.setOnClickListener {
            val login = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            val role = 0

            if (login == "" || password == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            else {
                val db = DbHelper(this, null)
                val isAuth = db.getUser(login, password, role)
                val isAdmin = db.getAdmin(login, password, role)

                if (isAuth) {
                    Toast.makeText(this, "Пользователь $login авторизован", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity3::class.java)
                    startActivity(intent)
                }
                else if (isAdmin){
                    Toast.makeText(this, "Пользователь $login (менеджер) авторизован", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity6::class.java)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this, "Пользователь $login не авторизован", Toast.LENGTH_LONG).show()
            }

        }
    }
}
