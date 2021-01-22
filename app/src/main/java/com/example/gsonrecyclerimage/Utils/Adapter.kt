package com.example.gsonrecyclerimage.Utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gsonrecyclerimage.Photo
import com.example.gsonrecyclerimage.R
import com.squareup.picasso.Picasso

class Adapter (var onClickItem: (photo: Int) -> Unit,
               private val arrayList: ArrayList<Photo>) : RecyclerView.Adapter<Adapter.AdapterHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.AdapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return AdapterHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {

        val currentPhoto: Photo = arrayList[position]

        holder.id.text = currentPhoto.id
        holder.title.text = currentPhoto.title
        Picasso.get().load(currentPhoto.url).fit().centerInside().into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class AdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var imageView: ImageView = itemView.findViewById(R.id.imageViewItem)
        var id: TextView = itemView.findViewById(R.id.idTextViewItem)
        var title: TextView = itemView.findViewById(R.id.titleTextViewItem)


    }


}
