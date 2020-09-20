package com.tuanhv.mvvmarch.sample.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.tuanhv.mvvmarch.base.platform.AppManager
import com.tuanhv.mvvmarch.sample.R
import com.tuanhv.mvvmarch.sample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by hoang.van.tuan on 8/7/18.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {

        private const val TAG = "MainActivity"

    }

    @Inject
    lateinit var appManager: AppManager

    private lateinit var mainBinding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initiateToolbar()
        setupNavigation()
    }

    private fun initiateToolbar() {
        setSupportActionBar(mainBinding.mainToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowCustomEnabled(true)

        mainBinding.actionBackBtn.setOnClickListener {
            onBackPressed()
        }

        mainBinding.actionSettingBtn.setOnClickListener {
            Navigation.findNavController(this, R.id.nav_host_main_fragment)
                    .navigate(R.id.openSetting)
        }
    }

    private fun setupNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_host_main_fragment)

        // Tie nav graph to items in bottom nav
        NavigationUI.setupWithNavController(main_bottom_nav_view, navController)
    }

    fun updateToolbar() {
        val navController = Navigation.findNavController(this, R.id.nav_host_main_fragment)

        when (navController.currentDestination?.id) {
            R.id.actionOne, R.id.actionTwo -> {
                mainBinding.mainToolbarTitle.text = getString(R.string.main_toolbar_title)
                mainBinding.actionBackBtn.visibility = View.INVISIBLE
                mainBinding.actionSettingBtn.visibility = View.VISIBLE
            }
            R.id.settingFragment -> {
                mainBinding.mainToolbarTitle.text = getString(R.string.setting_toolbar_title)
                mainBinding.actionBackBtn.visibility = View.VISIBLE
                mainBinding.actionSettingBtn.visibility = View.INVISIBLE
            }
        }
    }

    override fun onBackPressed() {
        val navController = Navigation.findNavController(this, R.id.nav_host_main_fragment)
        if (navController.currentDestination?.id != R.id.actionOne) {
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
