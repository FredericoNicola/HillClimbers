package com.example.hillclimbers

import android.content.Intent
import android.content.IntentSender
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.item_with_desc.view.*

class UserAdapter(options: FirestoreRecyclerOptions<PostModel>) :
    FirestoreRecyclerAdapter<PostModel, UserAdapter.UserAdapterVH>(options) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapterVH {
        return UserAdapterVH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_with_desc, parent, false)

        )
    }

    override fun onBindViewHolder(holder: UserAdapterVH, position: Int, model: PostModel) {
        holder.titledesc.text = model.road
        holder.descdesc.text = model.dist
        holder.linkdesc.text = model.link

        val titulo = holder.titledesc.toString()
        val desc = holder.descdesc.toString()
        val link = holder.linkdesc.toString()





    }

    class UserAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titledesc = itemView.desc_post_title
        var descdesc = itemView.desc_post_desc
        var linkdesc = itemView.link_desc

        var passdata = titledesc.text







      init {
           itemView.setOnClickListener { val position = adapterPosition

             println("isto é o titulo ${titledesc.text} \n isto é a desc ${descdesc.text} \n isto é o link ${linkdesc.text} \n isto é o id $position")
               val context = itemView.context
               val next = Intent()

               val intent = Intent(context, RoadDetails::class.java)

               intent.putExtra("road_name", "${titledesc.text}" )
               context.startActivity(intent)


               //val intent = intent.putExtra(titledesc).toString
              // context.startActivity(Intent(context, RoadDetails::class.java))

           }
        }
    }

}

