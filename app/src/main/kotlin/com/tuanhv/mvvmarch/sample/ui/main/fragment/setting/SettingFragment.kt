package com.tuanhv.mvvmarch.sample.ui.main.fragment.setting

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.tuanhv.mvvmarch.sample.R
import com.tuanhv.mvvmarch.sample.databinding.FragmentSettingBinding
import com.tuanhv.mvvmarch.sample.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by hoang.van.tuan on 8/20/18.
 */
class SettingFragment : Fragment() {

    private lateinit var settingBinding: FragmentSettingBinding
    private val settingViewModel: SettingViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        settingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        return settingBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).updateToolbar()

        initiateView()
        handleObservable()

        settingBinding.logoutBtn.setOnClickListener {
            settingViewModel.logout()
        }
    }

    private fun initiateView() {
        settingBinding.viewModel = settingViewModel
    }

    private fun handleObservable() {
        settingViewModel.getSuccessLogout().observe(
                viewLifecycleOwner,
                Observer {
                    NavHostFragment.findNavController(this).navigate(R.id.backToHome)
                    (activity as MainActivity).finish()
                }
        )

        settingViewModel.getErrorLogout().observe(
                viewLifecycleOwner,
                Observer {
                    Toast.makeText(activity, R.string.logout_fail, Toast.LENGTH_SHORT).show()
                }
        )
    }

}
