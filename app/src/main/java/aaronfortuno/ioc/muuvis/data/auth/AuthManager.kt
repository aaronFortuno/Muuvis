package aaronfortuno.ioc.muuvis.data.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class AuthManager {

    private val auth = FirebaseAuth.getInstance()

    fun register(
        email: String,
        password: String,
        onComplete: (Boolean) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true)
                } else {
                    onComplete(false)
                }
            }
    }

    fun login(
        email: String,
        password: String,
        onComplete: (Boolean) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true)
                    Log.e("AuthManager", "true: successful")
                } else {
                    onComplete(false)
                    Log.e("AuthManager", "false: error")
                }
            }
    }
}