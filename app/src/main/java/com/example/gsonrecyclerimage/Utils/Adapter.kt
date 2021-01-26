package com.example.gsonrecyclerimage.Utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.gsonrecyclerimage.Photo
import com.example.gsonrecyclerimage.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception
import com.squareup.picasso.Callback as Callback1

class Adapter(var onClickItem: (photo: String) -> Unit,
              private val arrayList: ArrayList<Photo>) : RecyclerView.Adapter<Adapter.AdapterHolder>(){

    //создает новый объект ViewHolder всякий раз, когда RecyclerView нуждается в этом.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.AdapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return AdapterHolder(view)
    }
    //принимает объект ViewHolder и устанавливает необходимые данные для соответствующей строки во view компоненте
    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {

        val currentPhoto: Photo = arrayList[position]

        holder.id.text = currentPhoto.id
        holder.title.text = currentPhoto.title
        Picasso.get().load(currentPhoto.url).fit().centerCrop().into(holder.imageView, object: com.squareup.picasso.Callback{
            override fun onSuccess() {
                holder.progressBarView.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                TODO("Not yet implemented")
            }

        })


        holder.imageView.setOnClickListener {
            onClickItem.invoke(currentPhoto.title)
        }


    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class AdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var imageView: ImageView = itemView.findViewById(R.id.imageViewItem)
        var id: TextView = itemView.findViewById(R.id.idTextViewItem)
        var title: TextView = itemView.findViewById(R.id.titleTextViewItem)
        var progressBarView: LottieAnimationView = itemView.findViewById(R.id.progressBarView)
    }
}
