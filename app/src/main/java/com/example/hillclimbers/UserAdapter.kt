package com.example.hillclimbers

import android.content.Intent
import android.net.Uri
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
        holder.title.text = model.road
        holder.desc.text = model.dist
        holder.link.text = model.link

    }

    class UserAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.desc_post_title
        var desc = itemView.desc_post_desc
        var link = itemView.link_desc

        init {
            itemView.setOnClickListener {
                Intent(Intent.ACTION_VIEW, Uri.parse("$link"))
            }
        }


    }
}

