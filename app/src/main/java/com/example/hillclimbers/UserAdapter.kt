package com.example.hillclimbers

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        holder.distdist.text = model.dist
        holder.linkdesc.text = model.link
        holder.descdesc.text = model.desc
        holder.timedesc.text = model.time
        holder.linkclick.text = model.link_map
        holder.stars.text = model.stars
        holder.nvotes.text = model.stars

    }

    class UserAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titledesc = itemView.desc_post_title
        var distdist = itemView.desc_post_desc
        var linkdesc = itemView.link_desc
        var descdesc = itemView.desc_desc
        var timedesc = itemView.time_desc
        var linkclick = itemView.link_click
        var stars = itemView.stars
        var nvotes = itemView.nvotes


      init {
           itemView.setOnClickListener { val position = adapterPosition
               val context = itemView.context
               val intent = Intent(context, RoadDetails::class.java)

               intent.putExtra("road_name", "${titledesc.text}" )
               intent.putExtra("road_desc","${descdesc.text}")
               intent.putExtra("link_desc", "${linkdesc.text}")
               intent.putExtra("time","${timedesc.text}")
               intent.putExtra("dist", "${distdist.text}")
               intent.putExtra("link_click", "${linkclick.text}")
               intent.putExtra("stars", "${stars.text}")
               intent.putExtra("nvotes", "${nvotes.text}")

               context.startActivity(intent)


           }
        }
    }
}

