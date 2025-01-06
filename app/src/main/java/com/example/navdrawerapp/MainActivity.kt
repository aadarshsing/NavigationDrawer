package com.example.navdrawerapp

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var   drawerLayout : DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.top_app_bar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            toolbar,
            R.string.open,
            R.string.close

        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()// this means if it open and user click then it closes and vice-versa


        supportActionBar?.setDisplayHomeAsUpEnabled(true) // home fragment will be enabled as first

        // functionality for the navDrawer's items
        val navView : NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener {
            it.isChecked = true

            when(it.itemId){
                R.id.home -> {
                    changeFragment(HomeFragment(),it.title.toString())
                }

                R.id.message -> {
                    changeFragment(MessageFragment(),it.title.toString())
                }
                R.id.login ->{
                    changeFragment(LoginFragment(),it.title.toString())
                }
                else -> {
                    changeFragment(SettingFragment(),it.title.toString())
                }
            }
            true
        }




    }
    private fun changeFragment(fragment: Fragment,title: String){

        val fragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout1,fragment)
        fragmentTransaction.commit()

        drawerLayout.closeDrawers() // close all the drawers that is opening
        setTitle(title) //it change the title of the activity or fragment
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(toggle.onOptionsItemSelected(item))
            true
        else super.onOptionsItemSelected(item)
    }
}