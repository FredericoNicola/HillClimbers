package com.example.hillclimbers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.hillclimbers.R.layout.fragment_home
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener


class HomeFragment : Fragment() {

    @Nullable
    @Override

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(fragment_home, container, false)
    }

    private var sampleImages = arrayOf(
        "https://firebasestorage.googleapis.com/v0/b/hillclimbers-ca97f.appspot.com/o/abarth.png?alt=media&token=e96d120d-6813-4a6c-bb3a-590b98f1fde8",
        "https://firebasestorage.googleapis.com/v0/b/hillclimbers-ca97f.appspot.com/o/audi.png?alt=media&token=9554c316-5f43-41c6-b336-f18352449d8f",
        "https://firebasestorage.googleapis.com/v0/b/hillclimbers-ca97f.appspot.com/o/fiestast.png?alt=media&token=a1ac982b-1199-4e05-80a5-37ca46e461df",
        "https://firebasestorage.googleapis.com/v0/b/hillclimbers-ca97f.appspot.com/o/fiestastvr.png?alt=media&token=893e27a7-7ce6-4598-a257-e45454bcae8b",
        "https://firebasestorage.googleapis.com/v0/b/hillclimbers-ca97f.appspot.com/o/fordperformance.png?alt=media&token=2e148173-dcaf-4e16-9984-d2c6155381ac",
        "https://firebasestorage.googleapis.com/v0/b/hillclimbers-ca97f.appspot.com/o/golfgti.png?alt=media&token=f3c513ff-c231-45ca-8dbe-3e30c90cf5d7"
    )
    private var carrinhos = arrayOf(
        "abarth",
        "audi",
        "fiestast",
        "fiestastvr",
        "fordperformance",
        "golfgti"
    )
    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            // You can use Glide or Picasso here
            Picasso.get().load(sampleImages[position]).into(imageView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val carouselView = view.findViewById(R.id.carouselView) as CarouselView;
        carouselView.setImageListener(imageListener);
        carouselView.setPageCount(sampleImages.size);


    }
}






