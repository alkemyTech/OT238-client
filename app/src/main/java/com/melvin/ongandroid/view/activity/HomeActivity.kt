package com.melvin.ongandroid.view.activity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_news, R.id.nav_about_us, R.id.nav_contact_us
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val home = navView.menu.findItem(R.id.nav_home)
        val contact = navView.menu.findItem(R.id.nav_contact_us)
        val about = navView.menu.findItem(R.id.nav_about_us)
        val news = navView.menu.findItem(R.id.nav_news)

        //TODO: Add fragments when they are ready
        home.setOnMenuItemClickListener {
            Toast.makeText(this, resources.getString(R.string.menu_home), Toast.LENGTH_SHORT).show()
            true
        }
        news.setOnMenuItemClickListener {
            Toast.makeText(this, resources.getString(R.string.menu_news), Toast.LENGTH_SHORT).show()
            true
        }
        about.setOnMenuItemClickListener {
            Toast.makeText(this, resources.getString(R.string.menu_about_us), Toast.LENGTH_SHORT).show()
            true
        }
        contact.setOnMenuItemClickListener {
            Toast.makeText(this, resources.getString(R.string.menu_contact_us), Toast.LENGTH_SHORT).show()
            true
        }




    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}