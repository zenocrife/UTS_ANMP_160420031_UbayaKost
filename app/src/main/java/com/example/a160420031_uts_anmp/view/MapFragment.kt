package com.example.a160420031_uts_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.a160420031_uts_anmp.R
import com.example.a160420031_uts_anmp.model.Kost
import com.example.a160420031_uts_anmp.viewmodel.ListDetailKostViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MapFragment : Fragment() {

    private lateinit var mMap: GoogleMap
    private lateinit var listKost: MutableList<Kost>
    private lateinit var marker: Marker
    private lateinit var viewModel: ListDetailKostViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ListDetailKostViewModel::class.java)


    }
}
