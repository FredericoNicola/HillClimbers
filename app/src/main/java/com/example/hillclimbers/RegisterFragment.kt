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
import androidx.fragment.app.Fragment
import com.example.hillclimbers.R.layout.activity_register
import com.example.hillclimbers.R.layout.fragment_home
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_register.*


class RegisterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(activity_register, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        buttonRegister_Register.setOnClickListener {
            when {
                TextUtils.isEmpty(email_register.text.toString().trim { it <= ' ' }) ->
                    Toast.makeText(
                        context,
                        "Please enter Email",
                        Toast.LENGTH_SHORT
                    ).show()
                TextUtils.isEmpty(editTextTextPassword.text.toString().trim { it <= ' ' }) ->
                    Toast.makeText(
                        context,
                        "Please enter a Password",
                        Toast.LENGTH_SHORT
                    ).show()
                else -> {

                    val email: String = email_register.text.toString().trim { it <= ' ' }
                    val password: String = editTextTextPassword.text.toString().trim { it <= ' ' }
                    val name: String = nameEditText.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                Toast.makeText(
                                    context,
                                    "You were Registered successfully.",
                                    Toast.LENGTH_SHORT
                                ).show()



                                val intent = Intent(context, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", firebaseUser.uid)
                                intent.putExtra("email_id", email)
                                intent.putExtra("name", name)
                                startActivity(intent)
                                fragmentManager?.beginTransaction()?.remove(this)?.commit()

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


    }
}






