package com.devproject.miguelfagundez.authfirebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/********************************************
 * Activity - InitialActivity
 * This activity check user credentials and
 *        show the login screen
 * @author: Miguel Fagundez
 * @date: April 10th, 2020
 * @version: 1.0
 * *******************************************/
class InitialActivity : AppCompatActivity() {

    private val loginFragment: LoginFragment = LoginFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        //************************************************************
        // Connecting with Firebase to check current user data
        //************************************************************
        if (FirebaseAuth.getInstance().currentUser != null && FirebaseAuth.getInstance().currentUser?.isEmailVerified == true)
            openHomeActivity()
        else if (FirebaseAuth.getInstance().currentUser != null && FirebaseAuth.getInstance().currentUser?.isEmailVerified == false) {
            Toast.makeText(this, getString(R.string.check_email_verification), Toast.LENGTH_SHORT).show()
            openLoginFragment()
        } else {
            openLoginFragment()
        }
    }

    private fun openLoginFragment() {
        // Calling login fragment (Animantion)
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.slide_out,
                R.anim.slide_in,
                R.anim.slide_out
            )
            .add(R.id.initial_frame_layout, loginFragment)
            .commit()
    }

    //***************************************************
    // Open second activity (checking firebase project)
    //***************************************************
    private fun openHomeActivity() {
        startActivity(Intent(this@InitialActivity, MainActivity::class.java))
        finish()
    }

    //************************************************************
    // Connecting with Firebase to register or checking user data
    //************************************************************
    fun registerNewUser(email:String, password:String) {
        Toast.makeText(this,getString(R.string.register_user_data),Toast.LENGTH_SHORT).show()
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                checkingTask(task)
            }
    }

    fun loginUser(email:String, password: String){
        Toast.makeText(this,getString(R.string.check_user_data),Toast.LENGTH_SHORT).show()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                checkingTask(task)
            }
    }

    //***************************************************
    // Checking Firebase results
    //***************************************************
    private fun checkingTask(task: Task<AuthResult>) {
        if (task.isSuccessful) {
            if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true) {
                // if credentials are accepted and email was verified
                // open a new activity
                openHomeActivity()
            } else {
                FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                Toast.makeText(this,getString(R.string.check_email_verification),Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
        }
    }
}
