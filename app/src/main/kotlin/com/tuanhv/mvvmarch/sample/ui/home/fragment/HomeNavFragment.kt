package com.tuanhv.mvvmarch.sample.ui.home.fragment

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.tuanhv.mvvmarch.sample.R
import com.tuanhv.mvvmarch.sample.databinding.FragmentHomeNavBinding
import com.tuanhv.mvvmarch.sample.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by hoang.van.tuan on 8/20/18.
 */
@AndroidEntryPoint
class HomeNavFragment : Fragment() {

    private lateinit var homeNavBinding: FragmentHomeNavBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeNavBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_nav, container, false)
        return homeNavBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as HomeActivity).updateToolbar()

        homeNavBinding.homeLoginBtn.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.openLogin)
        }

        homeNavBinding.homeRegisterBtn.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.openRegister)
        }
    }

}
