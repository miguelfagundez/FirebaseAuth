package com.devproject.miguelfagundez.authfirebaseapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivCloseRegister.setOnClickListener {
            parentFragment?.childFragmentManager?.popBackStack()
        }

        btnRegister.setOnClickListener {
            // if entries are not empty or null
            if(checkEntries()){
                //Registering user data in Initial Activity
                (activity as InitialActivity).registerNewUser(etRegisterUsername.text.toString(), etRegisterPassword.text.toString())
                parentFragment?.childFragmentManager?.popBackStack()
            }
        }
    }

    //******************************************
    // Checking entries in the register form
    //******************************************
    private fun checkEntries():Boolean{

        if (etRegisterUsername.text.isNullOrBlank() || etRegisterUsername.text.isNullOrEmpty() ||
            etRegisterConfirmUsername.text.isNullOrBlank() || etRegisterConfirmUsername.text.isNullOrEmpty() ||
            etRegisterPassword.text.isNullOrBlank() || etRegisterPassword.text.isNullOrEmpty() ||
            etRegisterConfirmPassword.text.isNullOrBlank() || etRegisterConfirmPassword.text.isNullOrEmpty()
        ) {
            Toast.makeText(activity?.applicationContext, getString(R.string.fields_empty), Toast.LENGTH_SHORT).show()
            return false
        } else {
            if (etRegisterPassword.text.toString() != etRegisterConfirmPassword.text.toString()){
                Toast.makeText(activity?.applicationContext, getString(R.string.error_password_match), Toast.LENGTH_SHORT).show()
                return false
            }else{
                if (etRegisterUsername.text.toString() != etRegisterConfirmUsername.text.toString()){
                    Toast.makeText(activity?.applicationContext, getString(R.string.error_email_match), Toast.LENGTH_SHORT).show()
                    return false
                }
            }
        }
        return true
    }

}
