package com.example.hillclimbers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.item_with_desc.*
import com.example.hillclimbers.UserAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.road_details.*

class RoadDetails : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.road_details)



        val roadtitle = intent.getStringExtra("road_name")
        val roaddesc = intent.getStringExtra("road_desc")
        var roadlink = intent.getStringExtra("link_desc")
        textView5.text = roadtitle
        road_desc.text = roaddesc

        fstore = FirebaseFirestore.getInstance()

        fstore.collection("allroads").get()

        val db = fstore

        println("$roadlink")

        val iframe = "$roadlink"


        web_view.settings.setJavaScriptEnabled(true);
        web_view.loadData(iframe, "text/html", "utf-8");




    }

    private lateinit var fstore: FirebaseFirestore


}