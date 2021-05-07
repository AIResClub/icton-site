package com.itmo.myapplication.screens.signup

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.itmo.myapplication.R
import com.itmo.myapplication.util.toast


class SignupFragment(val auth: FirebaseAuth) : Fragment(R.layout.signup_fragment) {

    private lateinit var signupViewModel: SignupViewModel

    private lateinit var emailText: TextView
    private lateinit var passwordText: TextView

    private lateinit var signButton: Button

    private lateinit var loading: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailText = view.findViewById(R.id.editTextEmailAddress)
        passwordText = view.findViewById(R.id.editTextPassword)
        signButton = view.findViewById(R.id.signInButton)
        loading = view.findViewById(R.id.loading)

        signupViewModel = ViewModelProviders.of(this).get(SignupViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        signupViewModel.isLoad.observe(this, Observer {
            loading.isVisible = it
            signButton.isEnabled = !it
        })

        signupViewModel.isSuccess.observe(this, Observer {
            if (it) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.remove(this)
                    ?.commit()
            } else {
                toast("Вы не вошли :(")
            }
        })

        signButton.setOnClickListener {
            createAccount(auth, emailText.text.toString(), passwordText.text.toString())
        }
    }

    private fun createAccount(auth: FirebaseAuth, email: String, password: String) {
        if (!validateForm()) {
            toast("Не все поля заполнены, уебище тупое")
            return
        }

        signupViewModel.createAccount(auth, email, password)
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email: String = emailText.text.toString()
        if (TextUtils.isEmpty(email)) {
            valid = false
        }

        val password: String = passwordText.text.toString()
        if (TextUtils.isEmpty(password)) {
            valid = false
        }

        return valid
    }


}