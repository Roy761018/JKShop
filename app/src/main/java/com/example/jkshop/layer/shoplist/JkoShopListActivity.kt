package com.example.jkshop.layer.shoplist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jkshop.R
import com.example.jkshop.databinding.ActivityShopListBinding
import com.example.jkshop.layer.shopdetail.JkoShopDetailActivity
import com.example.jkshop.layer.shopdetail.JkoShopDetailActivity.Companion.EXTRA_SHOP_ITEM_ID
import com.example.jkshop.util.JkShopStaticValue
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.system.exitProcess

class JkoShopListActivity: AppCompatActivity() {

    private lateinit var binding: ActivityShopListBinding

    private var shopListAdapter: JkoShopListAdapter? = null

    private val jkoShopListViewModel: JkoShopListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolBar()

        binding.tvUserName.text = getString(R.string.welcome_title, JkShopStaticValue.getNowUserName())

        with(binding.rvShopList) {
            layoutManager = LinearLayoutManager(this@JkoShopListActivity, LinearLayoutManager.VERTICAL, false)
            shopListAdapter = JkoShopListAdapter { item ->
                Intent(this@JkoShopListActivity, JkoShopDetailActivity::class.java).apply {
                    putExtra(EXTRA_SHOP_ITEM_ID, item.shopId)
                    startActivity(this)
                }
            }
            adapter = shopListAdapter
        }

        binding.ivMyInfo.setOnClickListener {
            // TODO My page
        }

        jkoShopListViewModel.getShopList()

        observeData()
    }

    private fun initToolBar() {
        with(binding.toolbar) {
            setSupportActionBar(this)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            navigationIcon = ContextCompat.getDrawable(this@JkoShopListActivity, R.drawable.chevron_left_sm_v2)?.apply {
                setTint(ContextCompat.getColor(this@JkoShopListActivity, R.color.white))
            }
            setNavigationOnClickListener {
                onBackPressed()
            }
            title = getString(R.string.toolbar_title_shop_list)
        }
    }



    private fun observeData() {
        jkoShopListViewModel.apply {
            getShopList.observe(this@JkoShopListActivity) {
                shopListAdapter?.shopList = it
                shopListAdapter?.notifyDataSetChanged()
            }
            errorAlert.observe(this@JkoShopListActivity) { retryAction ->
                AlertDialog.Builder(this@JkoShopListActivity).apply {
                    setTitle(getString(R.string.alert_title))
                    setMessage(getString(R.string.alert_msg))
                    setPositiveButton(getString(R.string.btn_ok)) { _, _ ->
                        retryAction.invoke()
                    }
                    setNegativeButton(getString(R.string.btn_cancel)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    if (!isFinishing) show()
                }
            }
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