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
import com.tuanhv.mvvmarch.sample.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by hoang.van.tuan on 8/20/18.
 */
class RegisterFragment : Fragment() {

    private val loginViewModel: RegisterViewModel by viewModel()
    private val homeViewModel: HomeViewModel by sharedViewModel()

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
