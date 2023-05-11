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
import com.example.a160420031_uts_anmp.model.Kost
import com.example.a160420031_uts_anmp.util.loadImage
import com.example.a160420031_uts_anmp.viewmodel.ListDetailKostViewModel
import kotlinx.android.synthetic.main.fragment_detail_kost.*
import kotlinx.android.synthetic.main.fragment_kost_list.*


class DetailKostFragment : Fragment() {
    private lateinit var viewModel: ListDetailKostViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_kost, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListDetailKostViewModel::class.java)
        if (arguments != null) {
            val kostId = DetailKostFragmentArgs.fromBundle(requireArguments()).kostId
            viewModel.fetch(kostId)
            observeViewModel()
            btnMap.setOnClickListener {
                val action = DetailKostFragmentDirections.actionMapFragment()
                Navigation.findNavController(it).navigate(action)
            }
        }

    }
    fun observeViewModel() { //function cek model.kt

        viewModel.kostLD.observe(viewLifecycleOwner, Observer {
            val arrKost = ArrayList<Kost>()
            arrKost.addAll(it)
            txtKostDetailName.setText(arrKost[0].name)
            txtPriceDetail.setText("Rp. " + arrKost[0].price +"/bulan")
            txtGenderDetail.setText("Gender :  "+arrKost[0].gender)
            txtRegionDetail.setText("Region :  "+arrKost[0].region)
            txtAlamatDetail.setText("Alamat :  "+arrKost[0].alamat)
            txtFasilitasDetail.setText("Fasilitas :  "+arrKost[0].fasilitas)
            txtRatingDetail.setText("Rating :  "+arrKost[0].rating)
            imgKostDetail.loadImage(arrKost[0].photoURL, progressBarKostDetailImg)

        })
    }
}
