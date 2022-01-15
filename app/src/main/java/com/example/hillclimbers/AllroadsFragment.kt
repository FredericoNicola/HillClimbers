package com.example.hillclimbers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_allroads.*


class AllroadsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_allroads, container, false)
    }


    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionReference: CollectionReference = db.collection("allRoads")
    var roadAdapter: UserAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerview()

        fun Fragment.addChildFragment(fragment: Fragment, frameId: Int) {

            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(frameId, fragment)
                .addToBackStack(null).commit()
        }

        btnAdd.setOnClickListener {
                var fieldFragment = AddRoadFragment()
                addChildFragment(fieldFragment, R.id.fragmet_field)
        }
    }


    fun setupRecyclerview(): RecyclerView? {
        val query: Query = collectionReference
        val firestoneRecyclerOptions: FirestoreRecyclerOptions<PostModel> =
            FirestoreRecyclerOptions.Builder<PostModel>()
                .setQuery(query, PostModel::class.java)
                .build()



        roadAdapter = UserAdapter(firestoneRecyclerOptions)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = roadAdapter
        return recyclerView
    }

    override fun onStart() {
        super.onStart()
        roadAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        roadAdapter!!.stopListening()
    }
}









