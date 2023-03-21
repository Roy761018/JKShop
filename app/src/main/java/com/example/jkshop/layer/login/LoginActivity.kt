package com.example.jkshop.layer.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jkshop.R
import com.example.jkshop.base.observeLiveData
import com.example.jkshop.base.showToastMsg
import com.example.jkshop.layer.shoplist.JkoShopListActivity
import com.example.jkshop.databinding.ActivityLoginBinding
import com.example.jkshop.model.UserEntity
import com.example.jkshop.util.JkShopStaticValue
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolBar()

        if (JkShopStaticValue.getNowUserName().isNotBlank()) {
            goToShopList()
            return
        }

        binding.btnLogin.setOnClickListener {
            viewModel.checkUserExist(userName = binding.etUserNameInput.text.toString())
        }

        observeData()
    }

    private fun initToolBar() {
        with(binding.toolbar) {
            setSupportActionBar(this)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            title = getString(R.string.btn_login)
        }
    }

    private fun observeData() {
        viewModel.apply {
            observeLiveData(isLoginSuccess) { success ->
                if (success) {
                    JkShopStaticValue.setNowUserName(binding.etUserNameInput.text.toString())
                    showToastMsg(getString(R.string.login_success_slogan))
                    goToShopList()
                } else {
                    showToastMsg(getString(R.string.login_fail))
                }
            }
            observeLiveData(isUserNotExist) {
                showToastMsg(getString(R.string.login_user_is_not_exist))
                viewModel.userRegister(UserEntity(uid = 0, userName = binding.etUserNameInput.text.toString()))
            }
        }
    }

    private fun goToShopList() {
        startActivity(Intent(this@LoginActivity, JkoShopListActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_HOME)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
            exitProcess(0)
        }
    }
}