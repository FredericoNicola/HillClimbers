package com.example.hillclimbers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.item_with_desc.*
import com.example.hillclimbers.UserAdapter
import kotlinx.android.synthetic.main.road_details.*

class RoadDetails : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.road_details)

        val roadnumber = intent.getStringExtra("titledesc")

        road_desc.text = roadnumber


    }


}