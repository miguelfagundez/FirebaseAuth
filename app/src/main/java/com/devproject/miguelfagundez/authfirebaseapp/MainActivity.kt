package com.devproject.miguelfagundez.authfirebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

/********************************************
 * Activity - MainActivity
 * This activity is used to test if user data
 *   is validated by Firebase project
 * @author: Miguel Fagundez
 * @date: April 10th, 2020
 * @version: 1.0
 * *******************************************/
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //************************************************************
        // Connecting with Firebase to logout user data
        //************************************************************
        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(this, getString(R.string.logout_message), Toast.LENGTH_SHORT).show()
            openInitialActivity()
        }
    }

    //***************************************************
    // Open initial activity (logins/register available)
    //***************************************************
    private fun openInitialActivity() {
        startActivity(Intent(this@MainActivity, InitialActivity::class.java))
        finish()
    }


}
