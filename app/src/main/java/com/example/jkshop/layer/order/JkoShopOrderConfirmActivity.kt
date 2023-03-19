package com.example.jkshop.layer.order

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.jkshop.R
import com.example.jkshop.databinding.ActivityOrderConfirmBinding
import com.example.jkshop.databinding.ItemOrderListBinding
import com.example.jkshop.model.ShopItemEntity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class JkoShopOrderConfirmActivity: AppCompatActivity() {

    companion object {
        private const val EXTRA_SHOP_ORDER_LIST = "EXTRA_SHOP_ORDER_LIST"
    }

    private lateinit var binding: ActivityOrderConfirmBinding

    private val viewModel: JkoShopOrderConfirmViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shopOrderList = intent.getParcelableArrayListExtra<ShopItemEntity>(EXTRA_SHOP_ORDER_LIST)

        initToolBar()
        initOrderListView(shopOrderList)

        binding.btnOrderConfirm.setOnClickListener {
            viewModel.orderConfirm()
        }

        observeData()
    }

    private fun initToolBar() {
        with(binding.toolbar) {
            setSupportActionBar(this)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            navigationIcon = ContextCompat.getDrawable(this@JkoShopOrderConfirmActivity, R.drawable.chevron_left_sm_v2)?.apply {
                setTint(ContextCompat.getColor(this@JkoShopOrderConfirmActivity, R.color.white))
            }
            setNavigationOnClickListener {
                onBackPressed()
            }
            title = getString(R.string.toolbar_title_order_confirm)
        }
    }

    private fun initOrderListView(shopOrderList: ArrayList<ShopItemEntity>?) {
        binding.llShopList.removeAllViews()

        shopOrderList?.forEach { item ->
            ItemOrderListBinding.inflate(LayoutInflater.from(this)).apply {
                tvName.text = item.name
                tvDescription.text = item.description
                tvPrice.text = item.price.toString()

                viewModel.setOrderPrice(item.price)
                viewModel.orderList.add(item)

                binding.llShopList.addView(this.root)
            }
        }
    }

    private fun observeData() {
        viewModel.apply {
            isSuccess.observe(this@JkoShopOrderConfirmActivity) {
                Toast.makeText(this@JkoShopOrderConfirmActivity, getString(R.string.order_commit_success_msg), Toast.LENGTH_SHORT).show()
                viewModel.clearShopCart()
            }
            setOrderPriceView.observe(this@JkoShopOrderConfirmActivity) { totalPrice ->
                binding.tvTotalMoney.text = getString(R.string.order_price, totalPrice.toString())
            }
        }
    }
}