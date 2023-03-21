package com.example.jkshop.layer.shopdetail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.jkshop.R
import com.example.jkshop.base.observeLiveData
import com.example.jkshop.base.showToastMsg
import com.example.jkshop.databinding.ActivityShopDetailBinding
import com.example.jkshop.layer.order.JkoShopOrderConfirmActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class JkoShopDetailActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_SHOP_ITEM_ID = "EXTRA_SHOP_ITEM_ID"
        private const val EXTRA_SHOP_ORDER_LIST = "EXTRA_SHOP_ORDER_LIST"
    }

    private lateinit var binding: ActivityShopDetailBinding

    private val viewModel: JkoShopDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolBar()

        viewModel.getShopDetail(intent.extras?.getString(EXTRA_SHOP_ITEM_ID, "").toString())

        binding.btnAddToCart.setOnClickListener {
            viewModel.addItemToShopCart()
        }

        binding.btnByNow.setOnClickListener {
            viewModel.getShopItem()?.let { shopItem ->
                Intent(this@JkoShopDetailActivity, JkoShopOrderConfirmActivity::class.java).apply {
                    putExtra(EXTRA_SHOP_ORDER_LIST, arrayListOf(shopItem))
                    startActivity(this)
                }
            }
        }

        observeData()
    }

    private fun initToolBar() {
        with(binding.toolbar) {
            setSupportActionBar(this)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            navigationIcon = ContextCompat.getDrawable(this@JkoShopDetailActivity, R.drawable.chevron_left_sm_v2)?.apply {
                setTint(ContextCompat.getColor(this@JkoShopDetailActivity, R.color.white))
            }
            setNavigationOnClickListener {
                onBackPressed()
            }
            title = getString(R.string.toolbar_title_shop_detail)
        }
    }

    private fun observeData() {
        viewModel.apply {
            observeLiveData(getShopItem) {
                binding.tvName.text = it.name
                binding.tvDescription.text = it.description
                binding.tvShopId.text = getString(R.string.shop_item_id_text, it.shopId)
                binding.tvPrice.text = getString(R.string.shop_item_price_text, it.price.toString())
            }
            observeLiveData(isAddSuccess) { isSuccess ->
                if (isSuccess) {
                    showToastMsg(getString(R.string.add_to_cart_success))
                    onBackPressed()
                } else {
                    showToastMsg(getString(R.string.add_to_cart_fail))
                }
            }
        }
    }
}