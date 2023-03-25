package com.example.jkshop.layer.my

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jkshop.R
import com.example.jkshop.base.observeLiveData
import com.example.jkshop.base.showAlertDialog
import com.example.jkshop.base.showToastMsg
import com.example.jkshop.databinding.ActivityMyInfoBinding
import com.example.jkshop.layer.login.LoginActivity
import com.example.jkshop.util.JkShopStaticValue
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyInfoActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMyInfoBinding

    private val viewModel: MyInfoViewModel by viewModel()

    private var myOrderHistoryAdapter: MyOrderHistoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolBar()

        binding.tvTitle.text = getString(R.string.my_title_view, JkShopStaticValue.getNowUserName())

        with(binding.rvOrderHistoryList) {
            layoutManager = LinearLayoutManager(this@MyInfoActivity, LinearLayoutManager.VERTICAL, false)
            myOrderHistoryAdapter = MyOrderHistoryAdapter { orderID ->
                viewModel.deleteOrderHistory(orderID)
            }
            addItemDecoration(DividerItemDecoration(this@MyInfoActivity, DividerItemDecoration.VERTICAL))
            adapter = myOrderHistoryAdapter
        }

        binding.btnLogOut.setOnClickListener {
            logout()
        }

        viewModel.getOrderHistory()
        observeData()
    }

    private fun logout() {
        if (!isFinishing) {
            showAlertDialog(getString(R.string.logout_message)) {
                JkShopStaticValue.setNowUserName("")
                finishAffinity()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }

    private fun initToolBar() {
        with(binding.toolbar) {
            setSupportActionBar(this)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            navigationIcon = ContextCompat.getDrawable(this@MyInfoActivity, R.drawable.chevron_left_sm_v2)?.apply {
                setTint(ContextCompat.getColor(this@MyInfoActivity, R.color.white))
            }
            setNavigationOnClickListener {
                onBackPressed()
            }
            title = getString(R.string.toolbar_title_my)
        }
    }

    private fun observeData() {
        viewModel.apply {
            observeLiveData(setOrderHistoryView) {
                if (it.isNotEmpty()) {
                    binding.tvNoResult.visibility = View.GONE
                    binding.rvOrderHistoryList.visibility = View.VISIBLE

                    myOrderHistoryAdapter?.orderList = it
                    myOrderHistoryAdapter?.notifyDataSetChanged()
                } else {
                    binding.tvNoResult.visibility = View.VISIBLE
                    binding.rvOrderHistoryList.visibility = View.GONE
                }
            }
            observeLiveData(isDeleteSuccess) { success ->
                showToastMsg(if (success) getString(R.string.delete_order_success) else getString(R.string.delete_order_fail))
            }
        }
    }
}