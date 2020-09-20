package com.tuanhv.mvvmarch.sample.ui.home.fragment.register

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tuanhv.mvvmarch.sample.R
import com.tuanhv.mvvmarch.sample.databinding.FragmentRegisterBinding
import com.tuanhv.mvvmarch.sample.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by hoang.van.tuan on 8/20/18.
 */
@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var registerBinding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        registerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        return registerBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as HomeActivity).updateToolbar()
    }

}
