package com.tuanhv.mvvmarch.sample.ui.main.fragment

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tuanhv.mvvmarch.sample.R
import com.tuanhv.mvvmarch.sample.databinding.FragmentMainNavBinding
import com.tuanhv.mvvmarch.sample.ui.main.MainActivity

/**
 * Created by hoang.van.tuan on 8/20/18.
 */
class MainNavFragment : Fragment() {

    private lateinit var mainNavBinding: FragmentMainNavBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainNavBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_nav, container, false)
        return mainNavBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).updateToolbar()
    }

}
