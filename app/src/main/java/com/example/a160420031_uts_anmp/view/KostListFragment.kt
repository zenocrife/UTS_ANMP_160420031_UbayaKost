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
import com.example.a160420031_uts_anmp.view.adapter.KostListAdapter
import com.example.a160420031_uts_anmp.viewmodel.ListKostViewModel
import kotlinx.android.synthetic.main.fragment_kost_list.*


class KostListFragment : Fragment() {

    private lateinit var viewModel: ListKostViewModel
    private val kostListadapter = KostListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kost_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListKostViewModel::class.java)
        viewModel.refresh()

        kostRecView.layoutManager = LinearLayoutManager(context)
        kostRecView.adapter = kostListadapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            kostRecView.visibility = View.GONE
            txtKostListError.visibility = View.GONE
            progressKostListLoad.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    fun observeViewModel() {
        viewModel.kostLD.observe(viewLifecycleOwner, Observer {
            kostListadapter.updateKostList(it)
        })

        viewModel.kostLoadLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                kostRecView.visibility = View.GONE
                progressKostListLoad.visibility = View.VISIBLE
            } else {
                kostRecView.visibility = View.VISIBLE
                progressKostListLoad.visibility = View.GONE
            }
        })
        viewModel.kostLoadErrLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtKostListError.visibility = View.VISIBLE
            } else {
                txtKostListError.visibility = View.GONE
            }

        })

    }

}