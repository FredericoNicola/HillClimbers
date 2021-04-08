package com.example.hillclimbers

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.nav_header.*


class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fragmentManager = supportFragmentManager
        val fragmentHome = HomeFragment()
        val fragmentAllroads = AllroadsFragment()
        //val activitytAccount = @LoginActivity
        val fragmentSocial = SocialFragment()


        // abrir a navbar
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //passagem entre fragments


        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().replace(R.id.frame_layout, fragmentHome)
                .commit()
        }

        navView.setNavigationItemSelectedListener {
            val fragmentTransaction = fragmentManager.beginTransaction()
            drawerLayout.closeDrawer(GravityCompat.START)
            when (it.itemId) {
                R.id.home -> fragmentTransaction.replace(R.id.frame_layout, fragmentHome)
                    .addToBackStack(null).commit()
                R.id.allroads -> fragmentTransaction.replace(R.id.frame_layout, fragmentAllroads)
                    .addToBackStack(null).commit()
                R.id.account -> startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                R.id.social -> fragmentTransaction.replace(R.id.frame_layout, fragmentSocial)
                    .addToBackStack(null).commit()
            }
            true
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}