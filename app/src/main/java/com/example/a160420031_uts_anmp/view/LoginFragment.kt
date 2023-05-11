package com.example.a160420031_uts_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420031_uts_anmp.R
import com.example.a160420031_uts_anmp.viewmodel.LoginViewModel
import com.example.a160420031_uts_anmp.viewmodel.UserProfileViewModel
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.fetch(txtID.text.toString(), txtPassword.text.toString())
        viewModel.userLD.observe(viewLifecycleOwner, Observer{ // retrieve data from  model.kt
            if(it.isNotEmpty()){
                id
                val action = LoginFragmentDirections.actionLoginFragment()
                Navigation.findNavController(view).navigate(action)
            }
            else{
                Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
