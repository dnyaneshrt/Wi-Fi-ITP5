package com.itp.mywifi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {

    var btn_login: Button?=null
    var btn_register: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_register=findViewById(R.id.btn_register)
        btn_login=findViewById(R.id.btn_login)

        btn_register!!.setOnClickListener {
            startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
        }

        btn_login!!.setOnClickListener {
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
        }

    }
}