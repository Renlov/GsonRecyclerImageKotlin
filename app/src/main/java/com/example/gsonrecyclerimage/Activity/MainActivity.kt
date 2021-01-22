package com.example.gsonrecyclerimage.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gsonrecyclerimage.Photo
import com.example.gsonrecyclerimage.R
import com.example.gsonrecyclerimage.Utils.Adapter
import com.example.gsonrecyclerimage.Utils.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var arrayList: ArrayList<Photo> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.setHasFixedSize(true)
        mAdapter = Adapter(onClickItem = {
            onClickMethod(it)
        }, arrayList)
        recyclerView!!.adapter = mAdapter

        getData()

        val itemTouchHelper =
            object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
                override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                    val positionOne: Int = p1.adapterPosition
                    val positionTwo: Int = p2.adapterPosition
                    Collections.swap(arrayList, positionOne, positionTwo)
                    mAdapter?.notifyItemMoved(positionOne, positionTwo)
                    return true
                }
                override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
                    val position: Int = p0.adapterPosition

                    arrayList.removeAt(position)
                    (mAdapter as Adapter).notifyItemRemoved(position)
                }
            }

        val itemTouch = ItemTouchHelper(itemTouchHelper)
        itemTouch.attachToRecyclerView(recyclerView)

    }


    private fun onClickMethod(title: String){
        val intent = Intent(this, TargetItemActivity::class.java)
        intent.putExtra("title", title)
        startActivity(intent)
    }

    private fun getData(){
        val call: Call<List<Photo>> = Client.getClient.getPhotos()
        call.enqueue(object : Callback<List<Photo>> {

            override fun onResponse(call: Call<List<Photo>>?, response: Response<List<Photo>>?) {
                arrayList.addAll(response!!.body()!!)
                recyclerView!!.adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Photo>>?, t: Throwable?) {
            }

        })
    }


}
