package com.example.hillclimbers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_social.*

class SocialFragment : Fragment() {

    @Nullable
    @Override

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_social, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button4.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://hillclimbers.pt"))
            startActivity(i)
        }
        button5.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/hillclimbers.pt"))
            startActivity(i)
        }


    }
}
