package com.sambataro.ignacio.anwbassesment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.sambataro.ignacio.anwbassesment.R
import com.sambataro.ignacio.anwbassesment.data.ApiCurrentUser
import com.sambataro.ignacio.anwbassesment.data.network.ConnectivityInterceptorImpl
import com.sambataro.ignacio.anwbassesment.data.network.OwnerNetworkDataSource
import com.sambataro.ignacio.anwbassesment.data.network.OwnerNetworkDataSourceImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

//        bottom_nav.setOnNavigationItemReselectedListener {
//            menuItem ->
//            when (menuItem.itemId) {
//
//            }
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(null, navController)
    }

}
