package com.example.hillclimbers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.android.synthetic.main.road_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RoadDetails : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.road_details)



        val roadtitle = intent.getStringExtra("road_name")
        val roaddesc = intent.getStringExtra("road_desc")
        var roadlink = intent.getStringExtra("link_desc")
        val time = intent.getStringExtra("time")
        val roaddist = intent.getStringExtra("dist")
        val map_link = intent.getStringExtra("link_click")
        val stars = intent.getStringExtra("stars")
        val nvotes = intent.getStringExtra("nvotes")
        textView5.text = "$roadtitle - $roaddist"
        road_desc.text = roaddesc
        roadtime.text = time




        println("$roadlink")

        val iframe = "$roadlink"


        web_view.settings.setJavaScriptEnabled(true);
        web_view.loadData(iframe, "text/html", "utf-8");
        web_view.isHorizontalScrollBarEnabled = false
        web_view.isVerticalScrollBarEnabled = false

        val linkbt = findViewById<Button>(R.id.map_link)
        linkbt.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("$map_link"))
            startActivity(i)

        }
    }




    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionReference: CollectionReference = db.collection("allroads")

    private fun updateStars(newMap: Map<String,Any>) = CoroutineScope(Dispatchers.IO).launch {
        val desc = intent.getStringExtra("road_desc")
        val dist = intent.getStringExtra("dist")
        val link = intent.getStringExtra("link_desc")
        val link_map = intent.getStringExtra("link_click")
        val road = intent.getStringExtra("road_name")
        val stars = intent.getStringExtra("stars")
        val time = intent.getStringExtra("time")
        val nvotes = intent.getStringExtra("nvotes")
        val roadQuery = collectionReference
            .whereEqualTo("desc", desc )
            .whereEqualTo("dist", dist )
            .whereEqualTo("link", link )
            .whereEqualTo("link_map", link_map )
            .whereEqualTo("road", road )
            .whereEqualTo("stars", stars )
            .whereEqualTo("nvotes", nvotes)
            .whereEqualTo("time", time )
            .get()
            .await()
        if (roadQuery.documents.isNotEmpty()){
            for(document in roadQuery){
                collectionReference.document(document.id).set(
                    newMap,
                    SetOptions.merge()
                )
            }
        }




    }







}