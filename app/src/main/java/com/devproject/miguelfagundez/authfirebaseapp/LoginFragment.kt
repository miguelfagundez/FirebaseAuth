package com.devproject.miguelfagundez.authfirebaseapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private val registerFragment = RegisterFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvRegister.setOnClickListener {
            childFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_up,
                    R.anim.slide_down,
                    R.anim.slide_up,
                    R.anim.slide_down
                )
                .add(R.id.login_frame, registerFragment)
                .addToBackStack(registerFragment.tag)
                .commit()
        }

        btnLogin.setOnClickListener {
            // if entries are not empty or null
            if(checkEntries()){
                (activity as InitialActivity).loginUser(etLoginUsername.text.toString(),
                etLoginPassword.text.toString())
            }
        }
    }

    //******************************************
    // Checking entries in the login form
    //******************************************
    private fun checkEntries(): Boolean {
        if(etLoginUsername.text.isNullOrEmpty() || etLoginUsername.text.isNullOrBlank() ||
                etLoginPassword.text.isNullOrBlank() || etLoginPassword.text.isNullOrEmpty()){
            return false
        }
        return true
    }
}
