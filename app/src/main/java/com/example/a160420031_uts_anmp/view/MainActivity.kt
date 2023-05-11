package com.example.a160420031_uts_anmp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.a160420031_uts_anmp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var dl: DrawerLayout
    private lateinit var nc: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dl = findViewById(R.id.drawerLayout)
        nc = (supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, nc,dl)
        bottomNav.setupWithNavController(nc)
        NavigationUI.setupWithNavController(navView, nc)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(nc, dl)
                || super.onSupportNavigateUp()
    }
}