package com.example.jkshop.layer.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.jkshop.layer.shoplist.JkoShopListActivity
import com.example.jkshop.databinding.ActivityLoginBinding
import com.example.jkshop.model.UserEntity
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            viewModel.checkUserExist(userName = binding.etUserNameInput.text.toString())
        }

        observeData()
    }

    private fun observeData() {
        viewModel.apply {
            isLoginSuccess.observe(this@LoginActivity) { success ->
                if (success) {
                    Toast.makeText(this@LoginActivity, "登入成功，歡迎使用", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, JkoShopListActivity::class.java))
                } else {
                    Toast.makeText(this@LoginActivity, "登入失敗", Toast.LENGTH_SHORT).show()
                }
            }
            isUserNotExist.observe(this@LoginActivity) {
                Toast.makeText(this@LoginActivity, "沒有您的資料，已幫你自動註冊", Toast.LENGTH_SHORT).show()
                viewModel.userRegister(UserEntity(uid = 0, userName = binding.etUserNameInput.text.toString()))
            }
        }
    }

    fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (T)-> Unit) {
        liveData.observe(this) {
            action.invoke(it)
        }
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