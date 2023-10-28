package aaronfortuno.ioc.muuvis.data.auth

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
        password: String
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                } else {

                }
            }
    }
}