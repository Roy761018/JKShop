package com.example.jkshop.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.jkshop.JkoShopListActivity
import com.example.jkshop.databinding.ActivityLoginBinding
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by inject()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.checkUser(binding.etUserNameInput.text.toString())

        observeData()
    }

    private fun observeData() {
        viewModel.apply {
            isLoginSuccess.observe(this@LoginActivity) { success ->
                if (success) {
                    startActivity(Intent(this@LoginActivity, JkoShopListActivity::class.java))
                }
            }
        }
    }

    fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (T)-> Unit) {
        liveData.observe(this) {
            action.invoke(it)
        }
    }
}