package com.example.a160420031_uts_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a160420031_uts_anmp.R
import com.example.a160420031_uts_anmp.model.Kost
import com.example.a160420031_uts_anmp.model.User
import com.example.a160420031_uts_anmp.util.loadImage
import com.example.a160420031_uts_anmp.util.loadImage2
import com.example.a160420031_uts_anmp.viewmodel.UserProfileViewModel
import kotlinx.android.synthetic.main.fragment_user_profile.*


class UserProfileFragment : Fragment() {
    private lateinit var viewModel: UserProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
        viewModel.fetch()
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            val arrUser = ArrayList<User>()
            arrUser.addAll(it)
            val firstName = arrUser[0].firstName ?: ""
            val lastName = arrUser[0].lastName ?: ""
            val fullName = if (firstName.isNotEmpty() && lastName.isNotEmpty()) "$firstName $lastName" else firstName + lastName
            txtNama.setText(fullName)
            txtEmail.setText(arrUser[0].email)
            txtAlamat.setText(arrUser[0].alamat)
            txtCreatedAtDate.setText("Created at : "+arrUser[0].createdAt)
            imgUserProfile.loadImage2(arrUser[0].profilePic)
        })
    }

}