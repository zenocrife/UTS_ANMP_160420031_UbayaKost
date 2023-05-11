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
import com.example.a160420031_uts_anmp.view.adapter.FavoriteKostAdapter
import com.example.a160420031_uts_anmp.viewmodel.FavoriteKostViewModel
import kotlinx.android.synthetic.main.fragment_favorite_list.*

class FavoriteKostFragment : Fragment() {

    private val favoriteAdapter = FavoriteKostAdapter(arrayListOf())
    private lateinit var viewModel: FavoriteKostViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoriteKostViewModel::class.java)
        viewModel.refresh()

        recViewFavorite.layoutManager = LinearLayoutManager(context)
        recViewFavorite.adapter = favoriteAdapter

        observeViewModel()

        //untuk menampilkan icon loading
        refreshLayoutFavorite.setOnRefreshListener {
            recViewFavorite.visibility = View.GONE
            txtFavoriteError.visibility = View.GONE
            progressLoadFavoriteKost.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutFavorite.isRefreshing = false
        }

    }

    fun observeViewModel() { //check type favorite boolean if its true or not
        viewModel.kostLD.observe(viewLifecycleOwner, Observer {
            favoriteAdapter.updateKostList(it)
        })

        viewModel.kostLoadErrLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                txtFavoriteError.visibility = View.VISIBLE
            } else {
                txtFavoriteError.visibility = View.GONE
            }
        })

        viewModel.kostLoadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                recViewFavorite.visibility = View.GONE
                progressLoadFavoriteKost.visibility = View.VISIBLE
            } else {
                recViewFavorite.visibility = View.VISIBLE
                progressLoadFavoriteKost.visibility = View.GONE
            }
        })
    }

}