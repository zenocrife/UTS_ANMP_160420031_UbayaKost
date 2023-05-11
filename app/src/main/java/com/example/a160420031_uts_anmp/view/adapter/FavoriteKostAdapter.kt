package com.example.a160420031_uts_anmp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420031_uts_anmp.R
import com.example.a160420031_uts_anmp.model.Kost
import com.example.a160420031_uts_anmp.util.loadImage
import com.example.a160420031_uts_anmp.view.FavoriteKostFragmentDirections
import com.example.a160420031_uts_anmp.view.FavoriteKostFragmentDirections.Companion.actionFavoriteKostFragment

class FavoriteKostAdapter(val favoriteKostList:ArrayList<Kost>)
    : RecyclerView.Adapter<FavoriteKostAdapter.FavoriteKostViewHolder>() {
    class FavoriteKostViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteKostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.kost_list_item, parent, false)
        return FavoriteKostViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteKostViewHolder, position: Int) {
        val kostnama = holder.view.findViewById<TextView>(R.id.txtKostName) // karena ada 2 id text view yang txtID maka pakai gini dan class nya ga nyambung ke layout apa-apa
        kostnama.text = favoriteKostList[position].name
        val rating = holder.view.findViewById<TextView>(R.id.txtRating)
        rating.text = favoriteKostList[position].rating
        val kostPrice = holder.view.findViewById<TextView>(R.id.txtPriceItem)
        kostPrice.text = "Rp "+ favoriteKostList[position].price.toString() +" /bulan"
        val imageKost  = holder.view.findViewById<ImageView>(R.id.imgKostItem)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarKostAdapter)
        imageKost.loadImage(favoriteKostList[position].photoURL, progressBar)

        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetails)
        btnDetail.setOnClickListener {
            var id = "0"
            favoriteKostList[position].id?.let{
                id = it
            }
            val action = FavoriteKostFragmentDirections.actionFavoriteKostFragment(id)
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun updateKostList(newKostList : ArrayList<Kost>) {
        favoriteKostList.clear()
        favoriteKostList.addAll(newKostList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return favoriteKostList.size
    }


}
