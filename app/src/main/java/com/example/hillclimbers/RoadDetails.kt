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
        textView5.text = roadtitle

        fstore = FirebaseFirestore.getInstance()

        fstore.collection("allroads").get()

        val db = fstore
    }

    private lateinit var fstore: FirebaseFirestore


}