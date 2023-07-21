package com.itp.mywifi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    var auth: FirebaseAuth?=null
    var et_username: EditText?=null
    var et_password: EditText?=null
    var btn_login: Button?=null
    var btn_register: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth=FirebaseAuth.getInstance()
        et_username=findViewById(R.id.et_username)
        et_password=findViewById(R.id.et_password)
        btn_register=findViewById(R.id.btn_register)
        btn_login=findViewById(R.id.btn_login)


        btn_register!!.setOnClickListener {
            startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
        }

        btn_login!!.setOnClickListener {
                if(et_username!!.text.toString().isEmpty()||et_password!!.text.toString().isEmpty())
                {
                    Toast.makeText(this,"please enter all fields", Toast.LENGTH_LONG).show()
                }
                else{
                    auth!!.signInWithEmailAndPassword(et_username!!.text.toString(),
                        et_password!!.text.toString()).addOnSuccessListener(OnSuccessListener {
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        Toast.makeText(this,"Logged in successfully", Toast.LENGTH_LONG).show()
                    }).addOnFailureListener(
                        OnFailureListener {
                            Toast.makeText(this,"invalid user. please try again", Toast.LENGTH_LONG).show()
                        })
                }
        }
    }
}