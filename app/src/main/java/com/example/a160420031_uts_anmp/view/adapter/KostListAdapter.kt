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
import com.example.a160420031_uts_anmp.view.KostListFragmentDirections

class KostListAdapter(val kostListArr:ArrayList<Kost>)
    : RecyclerView.Adapter<KostListAdapter.KostVH>(){
    class KostVH(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            KostVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.kost_list_item, parent, false)
        return KostVH(view)
    }

    override fun onBindViewHolder(holder: KostVH, position: Int){
        val kostItemNama = holder.view.findViewById<TextView>(R.id.txtKostName)
        val ratingKostItem = holder.view.findViewById<TextView>(R.id.txtRating)
        val priceItem = holder.view.findViewById<TextView>(R.id.txtPriceItem)
        val imgKostItem  = holder.view.findViewById<ImageView>(R.id.imgKostItem)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarKostAdapter)

        kostItemNama.text = kostListArr[position].name
        ratingKostItem.text = kostListArr[position].rating
        priceItem.text = "Rp. "+kostListArr[position].price.toString()+" /Tahun"
        imgKostItem.loadImage(kostListArr[position].photoURL, progressBar)

        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetails)
        btnDetail.setOnClickListener {
            var id = "0"
            kostListArr[position].id?.let{
                id = it
            }
            val action = KostListFragmentDirections.actionKostDetail(id)
            Navigation.findNavController(it).navigate(action)
        }
    }


    fun updateKostList(newKostList: ArrayList<Kost>) {
        kostListArr.clear()
        kostListArr.addAll(newKostList)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return kostListArr.size
    }
}