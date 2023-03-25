package com.example.jkshop.layer.shoplist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.jkshop.R
import com.example.jkshop.base.observeLiveData
import com.example.jkshop.base.showAlertDialog
import com.example.jkshop.databinding.ActivityShopListBinding
import com.example.jkshop.layer.my.MyInfoActivity
import com.example.jkshop.layer.shopcart.JkoShopCartActivity
import com.example.jkshop.layer.shopdetail.JkoShopDetailActivity
import com.example.jkshop.layer.shopdetail.JkoShopDetailActivity.Companion.EXTRA_SHOP_ITEM_ID
import com.example.jkshop.util.JkShopStaticValue
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.system.exitProcess

class JkoShopListActivity: AppCompatActivity() {

    private lateinit var binding: ActivityShopListBinding

    private var shopListAdapter: JkoShopListAdapter? = null

    private val jkoShopListViewModel: JkoShopListViewModel by viewModel()

    private val linearLayoutmanager: LinearLayoutManager by lazy {
        LinearLayoutManager(this@JkoShopListActivity, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolBar()

        binding.tvUserName.text = getString(R.string.welcome_title, JkShopStaticValue.getNowUserName())

        with(binding.rvShopList) {
            layoutManager = linearLayoutmanager
            shopListAdapter = JkoShopListAdapter { item ->
                Intent(this@JkoShopListActivity, JkoShopDetailActivity::class.java).apply {
                    putExtra(EXTRA_SHOP_ITEM_ID, item.shopId)
                    startActivity(this)
                }
            }
            adapter = shopListAdapter
            addOnScrollListener(object : OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        linearLayoutmanager.findLastVisibleItemPosition() == (linearLayoutmanager.itemCount - 1)) {
                        jkoShopListViewModel.currentPage++
                        jkoShopListViewModel.getShopList()
                    }
                }
            })
        }

        binding.ivMyInfo.setOnClickListener {
            startActivity(Intent(this@JkoShopListActivity, MyInfoActivity::class.java))
        }

        binding.btnGoToCart.setOnClickListener {
            startActivity(Intent(this@JkoShopListActivity, JkoShopCartActivity::class.java))
        }

        if (JkShopStaticValue.isNeedInitShopList()) {
            jkoShopListViewModel.initDefaultShopList()
        } else {
            jkoShopListViewModel.getShopList()
        }

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
            observeLiveData(getShopList) {
                shopListAdapter?.loadData(it)
            }
            observeLiveData(errorAlert) { retryAction ->
                if (!isFinishing) {
                    showAlertDialog(getString(R.string.alert_msg), retryAction)
                }
            }
            observeLiveData(initShopListSuccess) {
                jkoShopListViewModel.getShopList()
            }
        }
    }

    override fun onBackPressed() {
        showAlertDialog(getString(R.string.exit_message)) {
            Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(this)
                exitProcess(0)
            }
        }

    }
}