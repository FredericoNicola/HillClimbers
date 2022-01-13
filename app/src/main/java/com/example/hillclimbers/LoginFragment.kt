package com.example.hillclimbers

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.hillclimbers.R.layout.activity_login
import com.example.hillclimbers.R.layout.fragment_home
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_allroads.*


class LoginFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(activity_login, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun Fragment.addChildFragment(fragment: Fragment, frameId: Int) {

            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(frameId, fragment)
                .addToBackStack(null).commit()
        }

        buttonRegister.setOnClickListener {

            addChildFragment(RegisterFragment(), R.id.frame_layout)
        }


        buttonLogin.setOnClickListener {
            when {
                TextUtils.isEmpty(editTextTextEmailAddress.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        "Please enter a Email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(editTextTextPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        "Please enter a Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val email: String = editTextTextEmailAddress.text.toString().trim { it <= ' ' }
                    val password: String = editTextTextPassword.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "You are now logged in",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(context, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra(
                                    "user_id",
                                    FirebaseAuth.getInstance().currentUser!!.displayName
                                )
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    context,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }


        buttonRegister.setOnClickListener {
            startActivity(Intent(context, RegisterActivity::class.java))
        }

        //    val utilizador = Firebase.auth.currentUser
        //    utilizador?.let {
        //      for (profile in it.providerData) {
        //        val email = profile.email
        //      if (email != null){
        //    }
        //  }
        // }


        val user = Firebase.auth.currentUser
        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(context, MainActivity::class.java))
            Toast.makeText(
                context,
                "You are now logged out",
                Toast.LENGTH_SHORT
            ).show()
        }
        if (user != null) {
            logout.isVisible = true
            editTextTextEmailAddress.isVisible = false
            editTextTextPassword.isVisible = false
            buttonLogin.isVisible = false
            buttonRegister.isVisible = false
            textView2.isVisible = true

        }



    }
}






