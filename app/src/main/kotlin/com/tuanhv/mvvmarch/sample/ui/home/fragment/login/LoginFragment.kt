package com.tuanhv.mvvmarch.sample.ui.home.fragment.login

import androidx.lifecycle.ViewModelProvider
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.tuanhv.mvvmarch.sample.R
import com.tuanhv.mvvmarch.sample.databinding.FragmentLoginBinding
import com.tuanhv.mvvmarch.base.entity.User
import com.tuanhv.mvvmarch.sample.platform.SampleApplication
import com.tuanhv.mvvmarch.base.ui.BaseFragment
import com.tuanhv.mvvmarch.sample.ui.home.HomeActivity
import javax.inject.Inject

/**
 * Created by hoang.van.tuan on 8/20/18.
 */

class LoginFragment @Inject constructor() : BaseFragment() {

    @Inject
    lateinit var homeActivity: HomeActivity

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginBinding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return loginBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        homeActivity.updateToolbar()

        initiateView()
        handleObservable()

        loginBinding.loginBtn.setOnClickListener {
            val email = loginBinding.emailEdt.text.toString()
            val password = loginBinding.passwordEdt.text.toString()

            loginViewModel.login(email, password)
        }
    }

    private fun initiateView() {
        loginBinding.viewModel = loginViewModel
    }

    private fun handleObservable() {
        loginViewModel.getSuccessLogin().observe(
                viewLifecycleOwner,
                Observer {
                    SampleApplication.get(homeActivity)
                            .createUserComponent(User(it.accessToken, it.refreshToken, null, null, null))
                    NavHostFragment.findNavController(this).navigate(R.id.openMain)
                    homeActivity.finish()
                }
        )

        loginViewModel.getErrorLogin().observe(
                viewLifecycleOwner,
                Observer {
                    Toast.makeText(homeActivity, R.string.login_fail, Toast.LENGTH_SHORT).show()
                }
        )
    }

}
