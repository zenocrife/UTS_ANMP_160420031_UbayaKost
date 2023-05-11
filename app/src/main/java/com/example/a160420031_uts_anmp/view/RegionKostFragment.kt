package com.example.a160420031_uts_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160420031_uts_anmp.R
import com.example.a160420031_uts_anmp.view.adapter.RegionKostAdapter
import com.example.a160420031_uts_anmp.viewmodel.RegionKostViewModel
import kotlinx.android.synthetic.main.fragment_favorite_list.*
import kotlinx.android.synthetic.main.fragment_region_kost.*


class RegionKostFragment : Fragment() {

    private val regAdapter = RegionKostAdapter(arrayListOf())
    private lateinit var viewModel: RegionKostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_region_kost, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegionKostViewModel::class.java)
        viewModel.refresh()

        regionRecView.layoutManager = LinearLayoutManager(context)
        regionRecView.adapter = regAdapter

        observeViewModel()

        //untuk menampilkan icon loading
        refreshLayoutRegion.setOnRefreshListener {
            regionRecView.visibility = View.GONE
            txtRegionError.visibility = View.GONE
            progressLoadRegionKost.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutRegion.isRefreshing = false
        }

    }

    fun observeViewModel() { //check type favorite boolean if its true or not
        viewModel.regionLD.observe(viewLifecycleOwner, Observer {
            regAdapter.updateKostList(it)
        })

        viewModel.regionLoadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                txtRegionError.visibility = View.VISIBLE
            } else {
                txtRegionError.visibility = View.GONE
            }
        })

        viewModel.regionLoadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                regionRecView.visibility = View.GONE
                progressLoadRegionKost.visibility = View.VISIBLE
            } else {
                regionRecView.visibility = View.VISIBLE
                progressLoadRegionKost.visibility = View.GONE
            }
        })
    }

}