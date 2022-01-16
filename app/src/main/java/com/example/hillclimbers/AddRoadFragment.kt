package com.example.hillclimbers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
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

    private fun addRoadToFireStore(road: String, dist: String, desc: String, link: String, link_map: String, time: String, user: String) {
        val allRoads = hashMapOf("road" to road, "dist" to dist, "desc" to desc,"link" to link, "link_map" to link_map, "time" to time, "user_uid" to user)
        fStore.collection("allRoads")
            .add(allRoads as Map<String, Any>)

    }


    private lateinit var fStore: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fStore = FirebaseFirestore.getInstance();
        button2.setOnClickListener {
            val roadName: String = inputname.text?.trim().toString()
            val roadDist: String = inputkm.text?.trim().toString()
            val roadTime: String = inputtime.text?.trim().toString()
            val roadDesc: String = inputdesc.text?.trim().toString()
            val roadLink: String = inputlink.text?.trim().toString()
            val roadEmbeded: String = inputembeded.text?.trim().toString()
            val user: String = Firebase.auth.currentUser.toString()
            addRoadToFireStore(roadName, roadDist, roadDesc, roadEmbeded, roadLink, roadTime, user);
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









