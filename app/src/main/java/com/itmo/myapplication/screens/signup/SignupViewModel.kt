package com.itmo.myapplication.screens.signup

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.disposables.CompositeDisposable

class SignupViewModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()

    val isLoad = MutableLiveData<Boolean>(false)

    val isSuccess = MutableLiveData<Boolean>()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun createAccount(auth: FirebaseAuth, email: String, password: String) {
        isLoad.value = true

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            isLoad.value = false

            isSuccess.value = it.isSuccessful

            Log.d(TAG, "${it.result}")
        }
    }

    companion object {
        private const val TAG = "FIREBASE_AUTH"
    }
}
