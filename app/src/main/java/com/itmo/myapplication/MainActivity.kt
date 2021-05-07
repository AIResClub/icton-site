package com.itmo.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.itmo.myapplication.screens.signup.SignupFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        authStateListener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser

            if (user == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, SignupFragment(auth))
                    .commit()
            } else {
                val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

                bottomNavigationView.setOnNavigationItemReselectedListener { item ->
                    when(item.itemId) {
                        R.id.news -> null
                        R.id.forStudent -> null
                        R.id.askIct -> null
                        R.id.contacts -> null
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        auth.addAuthStateListener(authStateListener)
    }
}