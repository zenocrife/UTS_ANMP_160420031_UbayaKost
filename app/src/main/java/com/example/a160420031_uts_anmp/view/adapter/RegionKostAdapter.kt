package com.example.a160420031_uts_anmp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420031_uts_anmp.R
import com.example.a160420031_uts_anmp.model.Kost
import com.example.a160420031_uts_anmp.util.loadImage
import com.example.a160420031_uts_anmp.view.FavoriteKostFragmentDirections
import com.example.a160420031_uts_anmp.view.RegionKostFragment
import com.example.a160420031_uts_anmp.view.RegionKostFragmentDirections

class RegionKostAdapter(val regionKostList:ArrayList<Kost>)
    : RecyclerView.Adapter<RegionKostAdapter.RegionKostViewHolder>() {
    class RegionKostViewHolder(var view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RegionKostViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.kost_list_item, parent, false)
            return RegionKostViewHolder(view)


        }
    override fun onBindViewHolder(holder: RegionKostAdapter.RegionKostViewHolder, position: Int) {
        val kostnama = holder.view.findViewById<TextView>(R.id.txtKostName)
        val rating = holder.view.findViewById<TextView>(R.id.txtRating)
        val kostPrice = holder.view.findViewById<TextView>(R.id.txtPriceItem)
        val imageKost  = holder.view.findViewById<ImageView>(R.id.imgKostItem)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarKostAdapter)

        kostnama.text = regionKostList[position].name
        rating.text = "Rating :"+regionKostList[position].rating
        kostPrice.text = "Rp "+ regionKostList[position].price.toString() +" /bulan"
        imageKost.loadImage(regionKostList[position].photoURL, progressBar)

        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetails)
        btnDetail.setOnClickListener {
            var id = "0"
            regionKostList[position].id?.let{
                id = it
            }
            val action = RegionKostFragmentDirections.actionRegionKostFragment(id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateKostList(newKostList : ArrayList<Kost>) {
        regionKostList.clear()
        regionKostList.addAll(newKostList)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return regionKostList.size
    }



}




