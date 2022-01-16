package com.example.hillclimbers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_addroad.*


class AddRoadFragment : Fragment() {

    @Nullable
    @Override

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_addroad, container, false)
    }

    private fun addRoadToFireStore(road: String, dist: String, desc: String, link: String, link_map: String, time: String) {
        val allRoads = hashMapOf("road" to road, "dist" to dist, "desc" to desc,"link" to link, "link_map" to link_map, "time" to time)
        fStore.collection("allRoads")
            .add(allRoads as Map<String, Any>)

    }


    private lateinit var fStore: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fStore = FirebaseFirestore.getInstance();
        button2.setOnClickListener {
            var roadName: String = inputname.text?.trim().toString()
            var roadDist: String = inputkm.text?.trim().toString()
            var roadTime: String = inputtime.text?.trim().toString()
            var roadDesc: String = inputdesc.text?.trim().toString()
            var roadLink: String = inputlink.text?.trim().toString()
            var roadEmbeded: String = inputembeded.text?.trim().toString()
            addRoadToFireStore(roadName, roadDist, roadDesc, roadLink, roadEmbeded, roadTime);
            val fragment: Fragment? = childFragmentManager.findFragmentById(R.id.fragmet_field)
            val frag = AddRoadFragment()
            fragmentManager?.beginTransaction()?.remove(this)?.commit()

        }


        button3.setOnClickListener {
            val fragment: Fragment? = childFragmentManager.findFragmentById(R.id.fragmet_field)
            val frag = AddRoadFragment()
            fragmentManager?.beginTransaction()?.remove(this)?.commit()

        }
    }
}









