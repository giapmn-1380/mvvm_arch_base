package com.tuanhv.mvvmarch.sample.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.tuanhv.mvvmarch.base.platform.AppManager
import com.tuanhv.mvvmarch.sample.R
import com.tuanhv.mvvmarch.sample.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by hoang.van.tuan on 8/7/18.
 */
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    companion object {

        private const val TAG = "HomeActivity"

    }

    @Inject
    lateinit var appManager: AppManager

    private lateinit var homeBinding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        initiateToolbar()
    }

    private fun initiateToolbar() {
        setSupportActionBar(homeBinding.homeToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowCustomEnabled(true)

        homeBinding.actionBackBtn.setOnClickListener {
            onBackPressed()
        }
    }

    fun updateToolbar() {
        val navController = Navigation.findNavController(this, R.id.nav_host_home_fragment)

        when (navController.currentDestination?.id) {
            R.id.homeNavFragment -> {
                homeBinding.homeToolbarTitle.text = getString(R.string.home_toolbar_title)
                homeBinding.actionBackBtn.visibility = View.INVISIBLE
            }
            R.id.loginFragment -> {
                homeBinding.homeToolbarTitle.text = getString(R.string.login_toolbar_title)
                homeBinding.actionBackBtn.visibility = View.VISIBLE
            }
            R.id.registerFragment -> {
                homeBinding.homeToolbarTitle.text = getString(R.string.register_toolbar_title)
                homeBinding.actionBackBtn.visibility = View.VISIBLE
            }
        }
    }

    override fun onNavigateUp(): Boolean = findNavController(R.id.nav_host_home_fragment).navigateUp()

    override fun onBackPressed() {
        val navController = Navigation.findNavController(this, R.id.nav_host_home_fragment)
        if (navController.currentDestination?.id != R.id.homeNavFragment) {
            navController.popBackStack()
        } else {
            if (appManager.isBackPressFinish) {
                finish()
            } else {
                Toast.makeText(this, R.string.back_pressed_finish, Toast.LENGTH_SHORT).show()
            }
        }
    }

}
