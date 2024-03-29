package com.melvin.ongandroid.view.activity

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.facebook.login.LoginManager
import com.google.android.material.navigation.NavigationView
import com.melvin.ongandroid.R
import com.melvin.ongandroid.data.AppData
import com.melvin.ongandroid.databinding.ActivityHomeBinding
import com.melvin.ongandroid.databinding.NavHeaderHomeBinding
import com.melvin.ongandroid.domain.di.NetworkBroadcast
import com.melvin.ongandroid.domain.di.NetworkStatusService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    @Inject lateinit var appData : AppData
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private lateinit var headerBinding: NavHeaderHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Register Broadcast
        val networkFilter = IntentFilter()
        networkFilter.addAction("CONNECTIVITY_CHANGE")
        registerReceiver(NetworkBroadcast(), networkFilter)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        headerBinding = NavHeaderHomeBinding.bind(binding.navView.getHeaderView(0))

        setSupportActionBar(binding.appBarHome.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_home) as NavHostFragment
        val navController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_news,
                R.id.nav_testimonials,
                R.id.nav_about_us,
                R.id.nav_contact_us
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Load data from logged user

        headerBinding.tvUserName.text = appData.getPrefs("username")
        headerBinding.tvUserEmail.text = appData.getPrefs("email")

        val logout = headerBinding.tvLogOut
        val home = navView.menu.findItem(R.id.nav_home)
        val contact = navView.menu.findItem(R.id.nav_contact_us)
        val activities = navView.menu.findItem(R.id.nav_activities)
        val testimonials = navView.menu.findItem(R.id.nav_testimonials)
        val about = navView.menu.findItem(R.id.nav_about_us)
        val news = navView.menu.findItem(R.id.nav_news)

        //TODO: Add fragments when they are ready

        home.setOnMenuItemClickListener {
            Toast.makeText(this, resources.getString(R.string.menu_home), Toast.LENGTH_SHORT).show()
            true
        }
        news.setOnMenuItemClickListener {
            navController.navigate(R.id.action_nav_home_to_newsFragment)
            onBackPressed()
            true
        }
        activities.setOnMenuItemClickListener {
            navController.navigate(R.id.action_nav_home_to_activitieFragment)
            onBackPressed()
            true
        }
        testimonials.setOnMenuItemClickListener {
            onBackPressed()
            navController.navigate(R.id.action_nav_home_to_testimonialsFragment)
            onBackPressed()
            true
        }
        about.setOnMenuItemClickListener {
            navController.navigate(R.id.action_nav_home_to_usFragment)
            onBackPressed()
            true
        }
        contact.setOnMenuItemClickListener {
            navController.navigate(R.id.action_nav_home_to_contactFragment)
            onBackPressed()
            true
        }
        logout.setOnClickListener{
            logOut()
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
            moveTaskToBack(true)
        }
    }

    private fun logOut(){
        //Remove token and go to login
        if (appData.getPrefs("key") == "loggedWithFacebook"){
            LoginManager.getInstance().logOut()
            appData.clearPrefs()
        } else {
        appData.clearPrefs()
        }
        //create intent to go to login
        val intent = Intent(this, StartActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        //send intent to ignore timer
        intent.putExtra("STATUS", "LOGOUT")
        startActivity(intent)
        finish()
    }

    override fun onPause() {
        stopService(Intent(this, NetworkStatusService::class.java))
        super.onPause()
    }

    override fun onResume() {
        startService(Intent(this, NetworkStatusService::class.java))
        super.onResume()
    }

    override fun onStop() {
        stopService(Intent(this, NetworkStatusService::class.java))
        super.onStop()
    }

}