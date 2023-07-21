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

class RegisterActivity : AppCompatActivity() {

    var auth: FirebaseAuth?=null
    var et_username:EditText?=null
    var et_password:EditText?=null
    var btn_register: Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth=FirebaseAuth.getInstance()
        et_username=findViewById(R.id.et_usernameR)
        et_password=findViewById(R.id.et_passwordR)
        btn_register=findViewById(R.id.btn_registerR)

        btn_register!!.setOnClickListener {

            if(et_username!!.text.toString().isEmpty()||et_password!!.text.toString().isEmpty())
            {
                Toast.makeText(this,"please enter all fields",Toast.LENGTH_LONG).show()
            }
           else{
                auth!!.createUserWithEmailAndPassword(et_username!!.text.toString(),
                    et_password!!.text.toString()).addOnSuccessListener(OnSuccessListener {
                    Toast.makeText(this@RegisterActivity,"Registered successfully",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
                }).addOnFailureListener(
                    OnFailureListener {
                        Toast.makeText(this,"invalid user. please try again",Toast.LENGTH_LONG).show()
                    })
            }
        }

    }
}


